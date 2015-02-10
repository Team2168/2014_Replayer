Module DrawingData

    'Array Elements in Data(X)() Array
    '0 | Right Motor Data
    '1 | Left Motor Data
    '2 | Left Voltage
    '3 | Right Voltage
    '4 | Encoder Right
    '5 | Encoder Left 
    '6 | Encoder Right Difference
    '7 | Encoder Left Difference
    '8 | Gryo Angle
    '9 | Gyro Angle Difference

    Public Const PIXELS_PER_FOOT As Integer = 25
    Public FieldRectange As Rectangle = New Rectangle(New Point(10, 10), New Size(27 * PIXELS_PER_FOOT, 27 * PIXELS_PER_FOOT))
    Public HalfStep As Rectangle = New Rectangle(New Point(10, 10), New Size(27 * PIXELS_PER_FOOT, 1.08 * PIXELS_PER_FOOT))
    Public LandfillZone As Rectangle = New Rectangle(New Point(10, 10 + 1.08 * PIXELS_PER_FOOT), New Size(27 * PIXELS_PER_FOOT, 4.25 * PIXELS_PER_FOOT))
    Public Cross As Rectangle = New Rectangle(New Point(10 + ((27 * PIXELS_PER_FOOT) / 2), 10 + 13.58 * PIXELS_PER_FOOT), New Size(0.33 * PIXELS_PER_FOOT, 0.33 * PIXELS_PER_FOOT))
    Public Platform1 As Rectangle = New Rectangle(New Point(10, ((FieldRectange.Height / 2) - (3.25 * PIXELS_PER_FOOT))), New Size(15.58 * PIXELS_PER_FOOT, 1.66 * PIXELS_PER_FOOT))
    Public Platform2 As Rectangle = New Rectangle(New Point((FieldRectange.Width / 2) - 44, ((FieldRectange.Height / 2) + (3.25 * PIXELS_PER_FOOT))), New Size(Platform1.Size))

    Public Data As New ArrayList()

    Private CurrentMotorRightIndex As Integer = 0
    Private MotorRightMax As Integer = 0
    Private MotorDataRight As New ArrayList

    Private PositiveMotorValueEquation As Equation
    Private NegativeMotorValueEquation As Equation

    Public Function MotorValueToXYPoint(ByVal MotorValue As Double, ByVal Tick As Integer) As Point

        Dim p As Point

        If MotorValue > 0 Then
            p = New Point(Tick, PositiveMotorValueEquation.PlugInX(MotorValue))
        Else
            p = New Point(Tick, NegativeMotorValueEquation.PlugInX(MotorValue))
        End If

        Return p

    End Function

    Public Function ConvertMillisecondsToSeconds(ByVal milliseconds As Double) As Double
        Return TimeSpan.FromMilliseconds(milliseconds).TotalSeconds
    End Function

    Public Sub SetupMotorGraphs(ByRef g As Graphics, ByVal Panel As Panel, ByRef Font As Font, ByVal DataToDisplayPerFrame As Integer)

        g.DrawLine(Pens.Red, New Point(0, 1), New Point(Panel.Width, 1))
        g.DrawLine(Pens.Red, New Point(0, Panel.Height / 2), New Point(Panel.Width, Panel.Height / 2))
        g.DrawLine(Pens.Red, New Point(0, Panel.Height - 1), New Point(Panel.Width, Panel.Height - 1))

        MotorRightMax = DataToDisplayPerFrame
        MotorDataRight.Add(New Point(0, Panel.Height / 2))

        PositiveMotorValueEquation = New Equation(New Point(0, Panel.Height / 2), New Point(1, 0))
        NegativeMotorValueEquation = New Equation(New Point(0, Panel.Height / 2), New Point(0, Panel.Height))

    End Sub

    Public Sub DrawRightMotorData(ByRef g As Graphics, ByVal MotorValue As Double)

        If CurrentMotorRightIndex > MotorRightMax Then
            MotorDataRight.Clear()
            MotorDataRight.Add(New Point(0, 0))
            CurrentMotorRightIndex = 1
        Else
            MotorDataRight.Add(MotorValueToXYPoint(MotorValue, CurrentMotorRightIndex))
            CurrentMotorRightIndex += 1
        End If

        Dim points() As Point = DirectCast(MotorDataRight.ToArray(GetType(Point)), Point())
        g.DrawLines(Pens.White, points)

    End Sub

    Public Function RawHeadingToNormalHeading(ByVal Heading As Double) As Double

        Dim h As Double = Heading
        If h > 360 Then
            h = h - ((Math.Round(h / 360, 1)) * 360)
        End If

        If h < 0 Then
            While (h <= 0)
                h += 360
            End While
        End If

        Return h

    End Function

End Module
