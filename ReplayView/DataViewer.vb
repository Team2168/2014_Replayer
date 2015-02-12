Imports ZedGraph

Public Class DataViewer

    'Dim tg As Graphics

    Dim pointList As New PointPairList
    Dim myPane As GraphPane

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        bgwDrawElements.RunWorkerAsync()
        'tg = DisplayWindow.CreateGraphics

        myPane = ZedGraphControl1.GraphPane
        ' Set the titles and axis labels
        myPane.Title.Text = "Robot Chart Recreation"
        myPane.XAxis.Title.Text = "Inches"
        myPane.YAxis.Title.Text = "Inches"

        myPane.YAxis.Scale.MinAuto = True
        myPane.YAxis.Scale.MaxAuto = True

        myPane.XAxis.Scale.MinAuto = True
        myPane.XAxis.Scale.MaxAuto = True

        Dim myC As LineItem
        myC = myPane.AddCurve("RobotData", pointList, Color.Red)
        myC.Line.IsVisible = True
        pointList.Add(0, 0)
        ZedGraphControl1.Invalidate()
    End Sub

    Private Sub bgwDrawElements_DoWork(ByVal sender As System.Object, ByVal e As System.ComponentModel.DoWorkEventArgs) Handles bgwDrawElements.DoWork

        Dim MillisecondsElapsed As Integer = 0
        Dim TimeElapsed As Double = 0.0

        Dim startPoint As New Point(0, 0)

        For Each displayData In DrawingData.Data

            gcRightMotor.SetPointerValue("Pointer1", displayData(0))
            gcLeftMotor.SetPointerValue("Pointer1", displayData(1))

            GaugeControl1.SetPointerValue("Pointer1", RawHeadingToNormalHeading(displayData(6)))

            gcLeftSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(8) / 20)) * 1000) / 12))
            gcRightSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(7) / 20)) * 1000) / 12))

            Dim totalDistanceTraveled As Double = Math.Abs((CDbl(displayData(8)) + CDbl(displayData(7))) / 2)

            Dim x As Double = startPoint.X
            Dim y As Double = startPoint.Y
            ' Dim R = 6371
            'Dim brng = CDbl(RawHeadingToNormalHeading(displayData(6)))

            'x = DegreesToRadians(x)
            'y = DegreesToRadians(y)

            'Dim x2 As Double
            'Dim y2 As Double

            'x2 = Math.Asin(Math.Sin(x) * Math.Cos(totalDistanceTraveled / R) + _
            '              Math.Cos(x) * Math.Sin(totalDistanceTraveled / R) * Math.Cos(brng)) + x

            'y2 = y + Math.Atan2(Math.Sin(brng) * Math.Sin(totalDistanceTraveled / R) * Math.Cos(x), Math.Cos(totalDistanceTraveled / R) - Math.Sin(x) * Math.Sin(x)) + y

            'Process.Start("run.py", CStr(x) + " " + CStr(y) + " " + CStr(RawHeadingToNormalHeading(displayData(6))) + " " + CStr(totalDistanceTraveled
            '              ))
            'Shell("run.py " + CStr(x) + " " + CStr(y) + " " + CStr(RawHeadingToNormalHeading(displayData(6))))

            'If totalDistanceTraveled > 0 Then
            ' x = RadiansToDegrees((totalDistanceTraveled / 6371) * Math.Cos(DegreesToRadians(RawHeadingToNormalHeading(displayData(6))))) + startPoint.X
            'y = RadiansToDegrees((totalDistanceTraveled / (6371 * Math.Sin((x)))) * Math.Sin(DegreesToRadians(RawHeadingToNormalHeading(displayData(6))))) + startPoint.Y
            'Debug.Print("new point plotted")
            'End If

            'x = ((totalDistanceTraveled / 6371) * Math.Cos(Math.PI * CDbl(RawHeadingToNormalHeading(displayData(6)))) / 180) + startPoint.X
            'y = (totalDistanceTraveled / 6371 * Math.Sin((Math.PI * CDbl(x)) / 180)) * Math.Sin(RawHeadingToNormalHeading(displayData(6))) + startPoint.Y

            'startPoint.X = x2
            'startPoint.Y = y2

            x += RadiansToDegrees((0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Cos(DegreesToRadians(RawHeadingToNormalHeading(displayData(6)))))
            y += RadiansToDegrees((0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Sin(DegreesToRadians(RawHeadingToNormalHeading(displayData(6)))))

            'x += startPoint.X
            'y += startPoint.Y

            startPoint.X = x
            startPoint.Y = y

            pointList.Add(New PointPair(x, y))
            ZedGraphControl1.RestoreScale(myPane)

            ZedGraphControl1.Invalidate()

            Debug.Print(CStr(x) + "," + CStr(y))

            MillisecondsElapsed += 20
            TimeElapsed = Math.Round((ConvertMillisecondsToSeconds(MillisecondsElapsed) / 60), 2)
            lbCurrentTime.Text = CStr(TimeElapsed) + "min"
            'Threading.Thread.Sleep(20)

        Next

    End Sub


    Function RadiansToDegrees(ByVal value As Double) As Integer
        Try
            Return (180 * value) / Math.PI
        Catch ex As Exception
            Return 0
        End Try
    End Function


    Function DegreesToRadians(ByVal value As Double) As Double
        If (value * (Math.PI / 180)) = 0 Then
            Return 0
        Else
            Return value * (Math.PI / 180)
        End If
    End Function

End Class