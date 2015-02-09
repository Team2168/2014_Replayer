Public Class DisplayWindow

    Const PIXELS_PER_FOOT As Integer = 25

    Dim FieldRectange As Rectangle = New Rectangle(New Point(10, 10), New Size(27 * PIXELS_PER_FOOT, 27 * PIXELS_PER_FOOT))
    Dim HalfStep As Rectangle = New Rectangle(New Point(10, 10), New Size(27 * PIXELS_PER_FOOT, 1.08 * PIXELS_PER_FOOT))
    Dim LandfillZone As Rectangle = New Rectangle(New Point(10, 10 + 1.08 * PIXELS_PER_FOOT), New Size(27 * PIXELS_PER_FOOT, 4.25 * PIXELS_PER_FOOT))
    Dim Cross As Rectangle = New Rectangle(New Point(10 + ((27 * PIXELS_PER_FOOT) / 2), 10 + 13.58 * PIXELS_PER_FOOT), New Size(0.33 * PIXELS_PER_FOOT, 0.33 * PIXELS_PER_FOOT))
    Dim Platform1 As Rectangle = New Rectangle(New Point(10, ((FieldRectange.Height / 2) - (3.25 * PIXELS_PER_FOOT))), New Size(15.58 * PIXELS_PER_FOOT, 1.66 * PIXELS_PER_FOOT))
    Dim Platform2 As Rectangle = New Rectangle(New Point((FieldRectange.Width / 2) - 44, ((FieldRectange.Height / 2) + (3.25 * PIXELS_PER_FOOT))), New Size(Platform1.Size))

    Private Sub DisplayWindow_FormClosed(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosedEventArgs) Handles Me.FormClosed
        frmMain.Close()
    End Sub

    Private Sub DisplayWindow_Paint(ByVal sender As Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles Me.Paint

        Dim g As Graphics = Me.CreateGraphics

        'Draw the field Outline
        'g.DrawRectangle(Pens.Red, FieldRectange)

        'Draw half the step
        'g.DrawRectangle(Pens.White, HalfStep)
        'g.DrawString("Step", Me.Font, Brushes.White, New Point(10, 10))
        'g.FillRectangle(Brushes.White, New Rectangle(New Point(10, 10), New Size(27 * PIXELS_PER_FOOT, 1.08 * PIXELS_PER_FOOT)))

        'Landfill zone
        'g.DrawRectangle(Pens.Yellow, LandfillZone)
        'g.DrawString("Landfill Zone", Me.Font, Brushes.Yellow, New Point(10, 10 + 1.08 * PIXELS_PER_FOOT))

        'Cross
        g.DrawRectangle(Pens.Green, Cross)

        'Scoring Platforms
        g.DrawRectangle(Pens.Blue, Platform1)
        g.DrawRectangle(Pens.Blue, Platform2)

    End Sub
End Class