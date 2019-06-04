package com.smeup.web.interpreter;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.smeup.rpgparser.CommandLineProgram;
import com.smeup.rpgparser.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;

@Named
@RequestScoped
public class RpgController implements Serializable {

	/**
	 * 
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
		JavaSystemInterface.INSTANCE.addJavaInteropPackage("com.smeup.jd");
		
		rpgPreloadedValues = new LinkedHashMap<String,Object>();
		rpgPreloadedValues.put("", "");
		rpgPreloadedValues.put("Hello world", HardcodedRPG.HELLOWORLD);
		rpgPreloadedValues.put("Fibonacci", HardcodedRPG.FIBONACCI);
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

		final String rpgfileName = createTmpRpgleSourceFile(getRpgContent());
		commandLineProgram = RunnerKt.getProgram(rpgfileName);
		commandLineProgram.setTraceMode(false);

		List<String> parms = new ArrayList<String>();
		if(null != getRpgParmList() && !"".equals(getRpgParmList())) {
			String[] splitted = getRpgParmList().split("\\|");
			parms = Arrays.asList(splitted);
		}
		String response = executeRunnerKt(parms);
		setInterpretationOutput(response);
	}

	public String getInterpretationOutput() {
		return interpretationOutput;
	}

	public void setInterpretationOutput(String interpretationOutput) {
		this.interpretationOutput = interpretationOutput;
	}

	private String executeRunnerKt(final List<String> parms) {
		String response = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		Instant beginOperation = Instant.now();
		commandLineProgram.singleCall(parms);
		Instant endOperation = Instant.now();
		calculateElapsedTime(beginOperation, endOperation);
		System.out.flush();
		System.setOut(old);
		response = baos.toString();

		return response;
	}

	private String createTmpRpgleSourceFile(final String content) {
		String fileName = "";
		try {
			File temp = File.createTempFile("tempRpgFile", ".rpgle");
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			bw.write(content);
			bw.close();
			fileName = temp.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public String getRpgPreloaded() {
		return rpgPreloaded;
	}

	public void setRpgPreloaded(String rpgPreloaded) {
		this.rpgPreloaded = rpgPreloaded;
	}
	
	public void valueChangeMethod(AjaxBehaviorEvent abe){
		String preloadedRpg = (String) ((UIOutput)abe.getSource()).getValue();
		setRpgContent(preloadedRpg); 
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