

Public Class DataViewer

    Dim RightMotorGraphics As Graphics
    Dim LeftMotorGraphics As Graphics

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        bgwDrawElements.RunWorkerAsync()
    End Sub

    Private Sub bgwDrawElements_DoWork(ByVal sender As System.Object, ByVal e As System.ComponentModel.DoWorkEventArgs) Handles bgwDrawElements.DoWork

        Dim MillisecondsElapsed As Integer = 0
        Dim TimeElapsed As Double = 0.0

        Dim Prev

        For Each displayData In DrawingData.Data

            gcRightMotor.SetPointerValue("Pointer1", displayData(0))
            gcLeftMotor.SetPointerValue("Pointer1", displayData(1))

            GaugeControl1.SetPointerValue("Pointer1", RawHeadingToNormalHeading(displayData(6)))

            gcLeftSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(8) / 20)) * 1000) / 12))
            gcRightSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(7) / 20)) * 1000) / 12))

            Dim totalDistanceTraveled As Double = Math.Abs((CDbl(displayData(8)) + CDbl(displayData(7))) / 2)

            Dim x As Double
            Dim y As Double
            Dim heading As Double

            x = (0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Cos(RawHeadingToNormalHeading(displayData(6)))
            y = (0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Sin(RawHeadingToNormalHeading(displayData(6)))

            Debug.Print(CStr(x) + "," + CStr(y))


            MillisecondsElapsed += 20
            TimeElapsed = Math.Round((ConvertMillisecondsToSeconds(MillisecondsElapsed) / 60), 2)
            lbCurrentTime.Text = CStr(TimeElapsed) + "min"
            Threading.Thread.Sleep(20)
        Next

    End Sub

End Class