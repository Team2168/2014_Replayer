Public Class DisplayWindow

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
        'g.DrawRectangle(Pens.Green, DrawingData.Cross)

        'Scoring Platforms
        'g.DrawRectangle(Pens.Blue, DrawingData.Platform1)
        'g.DrawRectangle(Pens.Blue, DrawingData.Platform2)

    End Sub
End Class