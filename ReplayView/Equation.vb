Public Class Equation

    Private Slope As Double = 0.0
    Private YIntercept As Double = 0.0

    Public Sub New(ByVal Point1 As Point, ByVal Point2 As Point)

        Slope = (Point2.Y - Point1.Y) / (Point2.X - Point1.X)

        Dim y = Point2.Y
        Dim x = Point2.X
        Dim m = Slope

    End Sub

    Function PlugInX(ByVal X As Double) As Double
        Return (X * Slope) + YIntercept
    End Function

End Class
