<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frmMain
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.btnStartReplay = New System.Windows.Forms.Button()
        Me.btnStartConversion = New System.Windows.Forms.Button()
        Me.ProgressBar1 = New System.Windows.Forms.ProgressBar()
        Me.lbProgress = New System.Windows.Forms.Label()
        Me.bgwConvert = New System.ComponentModel.BackgroundWorker()
        Me.SuspendLayout()
        '
        'btnStartReplay
        '
        Me.btnStartReplay.Enabled = False
        Me.btnStartReplay.Location = New System.Drawing.Point(147, 12)
        Me.btnStartReplay.Name = "btnStartReplay"
        Me.btnStartReplay.Size = New System.Drawing.Size(75, 23)
        Me.btnStartReplay.TabIndex = 2
        Me.btnStartReplay.Text = "Start Replay"
        Me.btnStartReplay.UseVisualStyleBackColor = True
        '
        'btnStartConversion
        '
        Me.btnStartConversion.Location = New System.Drawing.Point(15, 12)
        Me.btnStartConversion.Name = "btnStartConversion"
        Me.btnStartConversion.Size = New System.Drawing.Size(126, 23)
        Me.btnStartConversion.TabIndex = 3
        Me.btnStartConversion.Text = "Start Conversion"
        Me.btnStartConversion.UseVisualStyleBackColor = True
        '
        'ProgressBar1
        '
        Me.ProgressBar1.Location = New System.Drawing.Point(15, 41)
        Me.ProgressBar1.Name = "ProgressBar1"
        Me.ProgressBar1.Size = New System.Drawing.Size(168, 23)
        Me.ProgressBar1.TabIndex = 12
        '
        'lbProgress
        '
        Me.lbProgress.AutoSize = True
        Me.lbProgress.Location = New System.Drawing.Point(189, 46)
        Me.lbProgress.Name = "lbProgress"
        Me.lbProgress.Size = New System.Drawing.Size(33, 13)
        Me.lbProgress.TabIndex = 13
        Me.lbProgress.Text = "100%"
        '
        'bgwConvert
        '
        '
        'frmMain
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(233, 71)
        Me.Controls.Add(Me.lbProgress)
        Me.Controls.Add(Me.ProgressBar1)
        Me.Controls.Add(Me.btnStartConversion)
        Me.Controls.Add(Me.btnStartReplay)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Name = "frmMain"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Replay Data"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents btnStartReplay As System.Windows.Forms.Button
    Friend WithEvents btnStartConversion As System.Windows.Forms.Button
    Friend WithEvents ProgressBar1 As System.Windows.Forms.ProgressBar
    Friend WithEvents lbProgress As System.Windows.Forms.Label
    Friend WithEvents bgwConvert As System.ComponentModel.BackgroundWorker

End Class
