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
			""),
    
    JD_001B("     V*=====================================================================\n" + 
    		"     V* Date      Release Au Description\n" + 
    		"     V* dd/mm/yy  nn.mm   xx Brief description\n" + 
    		"     V*=====================================================================\n" + 
    		"     V* 27/05/19  V5R1    AD Anonymous Developer\n" + 
    		"     V* 29/05/19  V5R1    AD More verbose with dsply\n" + 
    		"     V*=====================================================================\n" + 
    		"     H/COPY QILEGEN,£INIZH\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     I/COPY QILEGEN,£TABB£1DS\n" + 
    		"     I/COPY QILEGEN,£PDS\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * ENTRY\n" + 
    		"      * . Function\n" + 
    		"     D U$FUNZ          S             10\n" + 
    		"      * . Method\n" + 
    		"     D U$METO          S             10\n" + 
    		"      * . String\n" + 
    		"     D U$SVAR          S         210000\n" + 
    		"      * . Return Code ('1'=ERROR / blank=OK)\n" + 
    		"     D U$IN35          S              1\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * . Url\n" + 
    		"     D $$URL           S           1000\n" + 
    		"     D $X              S              3  0\n" + 
    		"     D $$SVAR          S                   LIKE(U$SVAR)\n" + 
    		"     D XXSVAR          S                   LIKE(U$SVAR)\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * Invoke Url\n" + 
    		"      * . Function\n" + 
    		"     D §§FUNZ          S             10\n" + 
    		"      * . Method\n" + 
    		"     D §§METO          S             10\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D DSP             S             50\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D* M A I N\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     C     *ENTRY        PLIST\n" + 
    		"     C                   PARM                    U$FUNZ\n" + 
    		"     C                   PARM                    U$METO\n" + 
    		"     C                   PARM                    U$SVAR\n" + 
    		"     C                   PARM                    U$IN35\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='JD_001B Entry parms:'                   COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='FUNZ='+%TRIM(U$FUNZ)+                   COSTANTE\n" + 
    		"     C                             ',METO='+%TRIM(U$METO)+\n" + 
    		"     C                             ',SVAR='+%TRIM(U$SVAR)+\n" + 
    		"     C                             ',IN35='+%TRIM(U$IN35)\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"      * Initial settings\n" + 
    		"     C                   EXSR      IMP0\n" + 
    		"      * Function / Method\n" + 
    		"1    C                   SELECT\n" + 
    		"      * Init\n" + 
    		"1x   C                   WHEN      U$FUNZ='INZ'\n" + 
    		"     C                   EXSR      FINZ\n" + 
    		"      * Invoke URL\n" + 
    		"1x   C                   WHEN      U$FUNZ='ESE'\n" + 
    		"     C                   EXSR      FESE\n" + 
    		"      * Detach (empty subroutine in this case)\n" + 
    		"1x   C                   WHEN      U$FUNZ='CLO'\n" + 
    		"     C                   EXSR      FCLO\n" + 
    		"1e   C                   ENDSL\n" + 
    		"      * Final settings\n" + 
    		"     C                   EXSR      FIN0\n" + 
    		"      * End\n" + 
    		"     C                   EVAL      DSP='END PROGRAM (RT)'                       COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   SETON                                        RT\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     C/COPY QILEGEN,£INZSR\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"    RD* Initial subroutine (as *INZSR)\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     £INIZI        BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='£INIZI EXECUTED'                        COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Initial settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     IMP0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='IMP0 EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Final settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FIN0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='FIN0 EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Init\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FINZ          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      U$IN35=*BLANKS\n" + 
    		"     C                   EVAL      $$SVAR=U$SVAR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='FINZ EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Invoke\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FESE          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      U$IN35=*BLANKS\n" + 
    		"      * Invoke url\n" + 
    		"     C                   EVAL      §§FUNZ='URL'\n" + 
    		"     C                   EVAL      §§METO='HTTP'\n" + 
    		"     C                   EVAL      XXSVAR=%TRIM($$SVAR)+U$SVAR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='CALLING PGM JD_URL'                     COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   CALL      'JD_URL'\n" + 
    		"     C                   PARM                    §§FUNZ\n" + 
    		"     C                   PARM                    §§METO\n" + 
    		"     C                   PARM                    XXSVAR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='JD_URL CALLED, FESE EXECUTED'           COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Detach\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FCLO          BEGSR\n" + 
    		"      *\n" + 
    		"      * This function doesn't do anything and is always successfull\n" + 
    		"     C                   EVAL      DSP='FCLO EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"");
 
    private String source;
 
    HardcodedRPG(String source) {
        this.source = source;
    }
 
    public String getSource() {
        return this.source;
    }
	

}
