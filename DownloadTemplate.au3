Sleep(6000)
   Local $hIE = WinGetHandle("[Class:IEFrame]")
   Local $hCtrl = ControlGetHandle($hIE, "", "[ClassNN:DirectUIHWND1]")

	If WinExists($hIE,"") Then
	  WinActivate($hIE,"")
	  ControlSend($hIE ,"",$hCtrl,"{F6}")          ; Gives focus to Open Button
	  Sleep(1500)
	  ControlSend($hIE ,"",$hCtrl,"{TAB}")          ; Gives focus to Save Button
	  Sleep(1500)
	  ControlSend($hIE ,"",$hCtrl,"{enter}")        ; Submit whatever control has focus
	  Sleep(8000)
	  ControlSend($hIE ,"",$hCtrl,"{F6}")          ; Gives focus to Open Button
	  Sleep(1500)
	  ControlSend($hIE ,"",$hCtrl,"{TAB}")          ; Gives focus to Open folder
	  Sleep(1500)
	  ControlSend($hIE ,"",$hCtrl,"{enter}")
	  Sleep(3000)

	  Local $hWnd3 = WinGetHandle("Downloads")
		If WinExists($hWnd3,"") Then
			WinActivate($hWnd3,"")
			Sleep(1500)
		
			If FileExists(@UserProfileDir & "\Downloads\DPT_STROK_PENS.rtf") Then
			   FileDelete(@UserProfileDir & "\Downloads\DPT_STROK_PENS.rtf")
			   WinClose($hWnd3)
			   Sleep(3000)
			   WinActivate($hIE,"")
			   ControlSend($hIE ,"",$hCtrl,"{F5}")		 
			
			Else
			   MsgBox(4096, "Error", "Файл не существует", 1)
			   WinClose($hWnd3)
			   WinActivate($hIE,"")
			   WinClose($hIE,"")
			EndIf
		Else
			MsgBox(4096, "Error", "Окно не существует", 1)
			WinActivate($hIE,"")
			WinClose($hIE,"")
		EndIf
	Else
	  MsgBox(4096, "Error", "Ошибка загрузки", 1)
	  WinActivate($hIE,"")
	  WinClose($hIE,"")
	EndIf
   Exit

