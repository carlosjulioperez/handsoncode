/* Generated by the Nim Compiler v2.2.0 */
var framePtr = null;
var excHandler = 0;
var lastJSError = null;

function toJSStr(s_p0) {
  var result_33556911 = null;

    var res_33556965 = newSeq__system_u2508((s_p0).length);
    var i_33556966 = 0;
    var j_33556967 = 0;
    Label1: {
        Label2: while (true) {
        if (!(i_33556966 < (s_p0).length)) break Label2;
          var c_33556968 = s_p0[i_33556966];
          if ((c_33556968 < 128)) {
          res_33556965[j_33556967] = String.fromCharCode(c_33556968);
          i_33556966 += 1;
          }
          else {
            var helper_33556994 = newSeq__system_u2508(0);
            Label3: {
                Label4: while (true) {
                if (!true) break Label4;
                  var code_33556995 = c_33556968.toString(16);
                  if ((((code_33556995) == null ? 0 : (code_33556995).length) == 1)) {
                  helper_33556994.push("%0");;
                  }
                  else {
                  helper_33556994.push("%");;
                  }
                  
                  helper_33556994.push(code_33556995);;
                  i_33556966 += 1;
                  if ((((s_p0).length <= i_33556966) || (s_p0[i_33556966] < 128))) {
                  break Label3;
                  }
                  
                  c_33556968 = s_p0[i_33556966];
                }
            };
++excHandler;
            try {
            res_33556965[j_33556967] = decodeURIComponent(helper_33556994.join(""));
--excHandler;
} catch (EXCEPTION) {
 var prevJSError = lastJSError;
 lastJSError = EXCEPTION;
 --excHandler;
            res_33556965[j_33556967] = helper_33556994.join("");
            lastJSError = prevJSError;
            } finally {
            }
          }
          
          j_33556967 += 1;
        }
    };
    if (res_33556965.length < j_33556967) { for (var i = res_33556965.length ; i < j_33556967 ; ++i) res_33556965.push(null); }
               else { res_33556965.length = j_33556967; };
    result_33556911 = res_33556965.join("");

  return result_33556911;

}

function rawEcho() {
          var buf = "";
      for (var i = 0; i < arguments.length; ++i) {
        buf += toJSStr(arguments[i]);
      }
      console.log(buf);
    

  
}

function newSeq__system_u2508(len_p0) {
  var result_33556944 = [];

  var F = {procname: "newSeq.newSeq", prev: framePtr, filename: "/Users/carper/.choosenim/toolchains/nim-2.2.0/lib/system.nim", line: 0};
  framePtr = F;
    F.line = 635;
    F.filename = "system.nim";
    result_33556944 = new Array(len_p0); for (var i = 0 ; i < len_p0 ; ++i) { result_33556944[i] = null; }  framePtr = F.prev;

  return result_33556944;

}
var F = {procname: "module n01_helloworld", prev: framePtr, filename: "/Users/carper/local/projects/handsoncode/nim/n01_helloworld.nim", line: 0};
framePtr = F;
F.line = 1;
F.filename = "n01_helloworld.nim";
rawEcho([72,101,108,108,111,32,87,111,114,108,100,33]);
framePtr = F.prev;
var F = {procname: "module n01_helloworld", prev: framePtr, filename: "/Users/carper/local/projects/handsoncode/nim/n01_helloworld.nim", line: 0};
framePtr = F;
framePtr = F.prev;
