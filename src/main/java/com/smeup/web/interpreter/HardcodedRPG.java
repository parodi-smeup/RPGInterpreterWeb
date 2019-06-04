package com.smeup.web.interpreter;

public enum HardcodedRPG {
	
    HELLOWORLD("﻿     D Msg             S             12\n" + 
			"     C                   Eval      Msg  = 'Hello World!'\n" + 
			"     C                   dsply                   Msg\n" + 
			"     C                   SETON                                          LR"),
    
    FIBONACCI("﻿      * Calculates number of Fibonacci in an iterative way\n" + 
			"     D ppdat           S              8\n" + 
			"     D NBR             S              8  0\n" + 
			"     D RESULT          S              8  0 INZ(0)\n" + 
			"     D COUNT           S              8  0\n" + 
			"     D A               S              8  0 INZ(0)\n" + 
			"     D B               S              8  0 INZ(1)\n" + 
			"     C     *entry        plist\n" + 
			"     C                   parm                    ppdat                          I\n" + 
			"      *\n" + 
			"     C                   Eval      NBR    = %Dec(ppdat : 8 : 0)\n" + 
			"     C                   EXSR      FIB\n" + 
			"     C                   clear                   dsp              50\n" + 
			"     C                   eval      dsp= 'FIBONACCI OF: ' +  ppdat +\n" + 
			"     C                                 ' IS: ' + %CHAR(RESULT)\n" + 
			"     C                   dsply                   dsp\n" + 
			"     C                   seton                                        lr\n" + 
			"      *--------------------------------------------------------------*\n" + 
			"     C     FIB           BEGSR\n" + 
			"     C                   SELECT\n" + 
			"     C                   WHEN      NBR = 0\n" + 
			"     C                   EVAL      RESULT = 0\n" + 
			"     C                   WHEN      NBR = 1\n" + 
			"     C                   EVAL      RESULT = 1\n" + 
			"     C                   OTHER\n" + 
			"     C                   FOR       COUNT = 2 TO NBR\n" + 
			"     C                   EVAL      RESULT = A + B\n" + 
			"     C                   EVAL      A = B\n" + 
			"     C                   EVAL      B = RESULT\n" + 
			"     C                   ENDFOR\n" + 
			"     C                   ENDSL\n" + 
			"     C                   ENDSR\n" + 
			"      *--------------------------------------------------------------*\n" + 
			"");
 
    private String source;
 
    HardcodedRPG(String source) {
        this.source = source;
    }
 
    public String getSource() {
        return this.source;
    }
	

}
