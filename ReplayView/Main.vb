Public Class frmMain

    Dim replayFilePath As String
    Dim replayFile() As String

    Dim prevEncoderRight As Double = 0.0
    Dim prevEncoderLeft As Double = 0.0
    Dim prevGyroAngle As Double = 0.0

    Dim Display As Boolean = True

    Private Sub frmMain_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Control.CheckForIllegalCrossThreadCalls = False

        Dim filePicker As New OpenFileDialog
        filePicker.ShowDialog()
        replayFilePath = filePicker.FileName

        replayFile = System.IO.File.ReadAllLines(replayFilePath)

        Dim timeElapsed As Double = ((replayFile.Count - 1) * (0.02)) / 60

    End Sub

    Private Sub btnStartConversion_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStartConversion.Click
        btnStartConversion.Enabled = False
        bgwConvert.RunWorkerAsync()
    End Sub

    Private Sub bgwConvert_DoWork(ByVal sender As System.Object, ByVal e As System.ComponentModel.DoWorkEventArgs) Handles bgwConvert.DoWork

        Dim count As Integer = 0
        Dim max As Integer = replayFile.Count

        For Each line As String In replayFile

            Dim displayArray() As String = {"", "", "", "", "", "", "", "", "", "", ""}
            displayArray(0) = line.Split(":")(0).Split(",")(0)
            displayArray(3) = "Unavalibal"

            displayArray(1) = line.Split(":")(0).Split(",")(1)
            displayArray(2) = "Unavalibal"

            displayArray(4) = line.Split(":")(1).Split(",")(0)
            displayArray(7) = CDbl(displayArray(4) - prevEncoderRight)
            prevEncoderRight = CDbl(displayArray(4))

            displayArray(5) = line.Split(":")(1).Split(",")(1)
            displayArray(8) = CDbl(displayArray(5) - prevEncoderLeft)
            prevEncoderLeft = CDbl(displayArray(5))

            displayArray(6) = line.Split(":")(2)
            displayArray(9) = CDbl(displayArray(6) - prevGyroAngle)
            prevGyroAngle = CDbl(displayArray(6))

            lbProgress.Text = CStr(Math.Round(count / max, 3) * 100) + "%"
            ProgressBar1.Value = Math.Round(count / max, 3) * 100
            count += 1
            DrawingData.Data.Add(displayArray)

        Next

        btnStartReplay.Enabled = True
        btnStartConversion.Enabled = False

    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        If (Display) Then
            Display = False
        Else
            Display = True
        End If
    End Sub

    Private Sub btnStartReplay_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStartReplay.Click
        'DisplayWindow.Show()
        DataViewer.Show()

        Me.Hide()
    End Sub

End Class
