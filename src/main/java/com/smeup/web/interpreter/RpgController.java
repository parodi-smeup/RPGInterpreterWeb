package com.smeup.web.interpreter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.RuntimeErrorException;

import com.smeup.rpgparser.CommandLineProgram;
import com.smeup.rpgparser.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;

@Named
@RequestScoped
public class RpgController implements Serializable {

	/**
	 * WARNING: On Windows remember to start payara with this JVM option:
	 *  -Dfile.encoding=UTF-8
	 */
	private static final long serialVersionUID = 1L;
	private CommandLineProgram commandLineProgram;

	@Inject
	private RpgSource rpgSource;

	private String interpretationOutput;
	
	private String rpgPreloaded;
	private Map<String,Object> rpgPreloadedValues;
	
	private String elapsedTime;
	@PostConstruct
	public void initPreloadedContent() {
		// load all Jd_* programs (a java programm called as an RPG from an interpreted RPG)

		rpgPreloadedValues = new LinkedHashMap<String,Object>();
		rpgPreloadedValues.put("", "");
		rpgPreloadedValues.put("Hello world", HardcodedRPG.HELLOWORLD.getSource());
		rpgPreloadedValues.put("Fibonacci", HardcodedRPG.FIBONACCI.getSource());
		rpgPreloadedValues.put("JD_001B", HardcodedRPG.JD_001B.getSource());
	}

	public Map<String,Object> getRpgPreloadedValue() {
		return rpgPreloadedValues;
	}
	
	public String getFavCoffee2() {
		return rpgPreloaded;
	}
	
	public String getRpgContent() {
		return rpgSource.getContent();
	}

	public void setRpgContent(final String content) {
		rpgSource.setContent(content);
	}
	
	public String getRpgParmList() {
		return rpgSource.getRpgParmList();
	}

	public void setRpgParmList(final String parmList) {
		rpgSource.setRpgParmList(parmList);
	}
	
	public void interpretate() {
		String rpgSource = lineEndingConversion(getRpgContent());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		JavaSystemInterface javaSystemInterface = new JavaSystemInterface(ps);
		javaSystemInterface.addJavaInteropPackage("com.smeup.jd");
		
		commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);
		commandLineProgram.setTraceMode(false);

		List<String> parms = new ArrayList<String>();
		if(null != getRpgParmList() && !"".equals(getRpgParmList())) {
			String[] splitted = getRpgParmList().split("\\|");
			parms = Arrays.asList(splitted);
		}
		String response = executeRunnerKt(parms, baos);
		baos.reset();
		setInterpretationOutput(response);
	}


	private String lineEndingConversion(String rpgContent) {
		Scanner scanner = new Scanner(rpgContent);
		StringBuilder result = new StringBuilder();
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  result.append(line);
		  result.append(System.lineSeparator());
		}
		scanner.close();
		return result.toString();
	}

	public String getInterpretationOutput() {
		return interpretationOutput;
	}

	public void setInterpretationOutput(String interpretationOutput) {
		this.interpretationOutput = interpretationOutput;
	}

	private String executeRunnerKt(final List<String> parms, ByteArrayOutputStream baos) {
		Instant beginOperation = Instant.now();
		commandLineProgram.singleCall(parms);
		Instant endOperation = Instant.now();
		calculateElapsedTime(beginOperation, endOperation);
		return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}

	public String getRpgPreloaded() {
		return rpgPreloaded;
	}

	public void setRpgPreloaded(String rpgPreloaded) {
		this.rpgPreloaded = rpgPreloaded;
	}
	
	public void valueChangeMethod(AjaxBehaviorEvent abe){
		setRpgContent(programFromGUI(abe));
	}

	private String programFromGUI(AjaxBehaviorEvent abe) {
		return (String) ((UIOutput)abe.getSource()).getValue();
	}
	
	private void calculateElapsedTime(final Instant beginOperation, final Instant endOperation) {
		Duration duration = Duration.between(beginOperation, endOperation);
		setElapsedTime("Started: " + beginOperation.toString() + "<br>Ended: " + endOperation + "<br>Elapsed: " + humanReadableFormat(duration));
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}
	
	public static String humanReadableFormat(Duration duration) {
	    return duration.toString()
	            .substring(2)
	            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
	            .toLowerCase();
	}

}