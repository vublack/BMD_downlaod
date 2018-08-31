Sleep(5000)

	Local $hIE = WinGetHandle("[Class:IEFrame]")
	Local $hCtrl = ControlGetHandle($hIE, "", "[ClassNN:DirectUIHWND1]")

	If WinExists($hIE,"") Then
		WinActivate($hIE,"")
		ControlSend($hIE ,"",$hCtrl,"{F6}")          ; Gives focus to Open Button
		Sleep(500)
		ControlSend($hIE ,"",$hCtrl,"{enter}")        ; Submit whatever control has focus
	EndIf
	Sleep(25000)