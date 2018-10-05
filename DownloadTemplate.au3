Sleep(5000)
   Local $hIE = WinGetHandle("[Class:IEFrame]")
   Local $hCtrl = ControlGetHandle($hIE, "", "[ClassNN:DirectUIHWND1]")

   If WinExists($hIE,"") Then
	  WinActivate($hIE,"")
	  ControlSend($hIE ,"",$hCtrl,"{F6}")          ; Gives focus to Open Button
	  Sleep(500)
	  ControlSend($hIE ,"",$hCtrl,"{enter}")        ; Submit whatever control has focus
   EndIf
   Sleep(5000)

   Local $hWnd3 = WinGetHandle("DPT_STROK_PENS (защищенный просмотр) - Word")
   If WinExists($hWnd3,"") Then
	  WinActivate($hWnd3,"")
	  Sleep(5000)
	  WinClose($hWnd3)
	  Sleep(2000)
	  WinActivate($hIE,"")
	  ControlSend($hIE ,"",$hCtrl,"{F5}")
   Else
	  MsgBox(4096, "Error", "Окно не существует", 1)
	  WinActivate($hIE,"")
	  WinClose($hIE,"")
   EndIf
   Exit

