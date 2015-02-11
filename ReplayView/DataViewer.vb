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
        pointList.Add(0, 0)
        ZedGraphControl1.Invalidate()
    End Sub

    Private Sub bgwDrawElements_DoWork(ByVal sender As System.Object, ByVal e As System.ComponentModel.DoWorkEventArgs) Handles bgwDrawElements.DoWork

        Dim MillisecondsElapsed As Integer = 0
        Dim TimeElapsed As Double = 0.0

        Dim PrevPoint As New Point(0, 0)

        For Each displayData In DrawingData.Data

            gcRightMotor.SetPointerValue("Pointer1", displayData(0))
            gcLeftMotor.SetPointerValue("Pointer1", displayData(1))

            GaugeControl1.SetPointerValue("Pointer1", RawHeadingToNormalHeading(displayData(6)))

            gcLeftSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(8) / 20)) * 1000) / 12))
            gcRightSpeed.SetPointerValue("Pointer1", Math.Abs(((CDbl(displayData(7) / 20)) * 1000) / 12))

            Dim totalDistanceTraveled As Double = Math.Abs((CDbl(displayData(8)) + CDbl(displayData(7))) / 2)

            Dim x As Double
            Dim y As Double

            x = (0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Cos(RawHeadingToNormalHeading(displayData(6)))
            y = (0.5) * Math.Pow(totalDistanceTraveled, 2) * Math.Sin(RawHeadingToNormalHeading(displayData(6)))

            x += PrevPoint.X
            y += PrevPoint.Y

            'DrawSegment(g, PrevPoint, New Point(x, y))

            'DrawSegment(tg, PrevPoint, New Point(x, y))
            'DrawPoint(g, PrevPoint)
            PrevPoint = New Point(x, y)
            'DrawPoint(tg, PrevPoint)

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

    Private Sub DataViewer_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class