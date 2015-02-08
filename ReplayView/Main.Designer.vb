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
        Me.ListView1 = New System.Windows.Forms.ListView()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.lbReady = New System.Windows.Forms.Label()
        Me.chRightJoystick = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chLeftJoystick = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chLeftMotors = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chRightMotors = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chRightEncoder = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chLeftEncoder = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chGyro = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chRightEncoderChange = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chLeftEncoderChange = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.chGyroChange = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.Button1 = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'btnStartReplay
        '
        Me.btnStartReplay.Enabled = False
        Me.btnStartReplay.Location = New System.Drawing.Point(617, 280)
        Me.btnStartReplay.Name = "btnStartReplay"
        Me.btnStartReplay.Size = New System.Drawing.Size(75, 23)
        Me.btnStartReplay.TabIndex = 2
        Me.btnStartReplay.Text = "Start Replay"
        Me.btnStartReplay.UseVisualStyleBackColor = True
        '
        'btnStartConversion
        '
        Me.btnStartConversion.Location = New System.Drawing.Point(1146, 12)
        Me.btnStartConversion.Name = "btnStartConversion"
        Me.btnStartConversion.Size = New System.Drawing.Size(99, 23)
        Me.btnStartConversion.TabIndex = 3
        Me.btnStartConversion.Text = "Start Conversion"
        Me.btnStartConversion.UseVisualStyleBackColor = True
        '
        'ProgressBar1
        '
        Me.ProgressBar1.Location = New System.Drawing.Point(15, 251)
        Me.ProgressBar1.Name = "ProgressBar1"
        Me.ProgressBar1.Size = New System.Drawing.Size(1191, 23)
        Me.ProgressBar1.TabIndex = 12
        '
        'lbProgress
        '
        Me.lbProgress.AutoSize = True
        Me.lbProgress.Location = New System.Drawing.Point(1212, 255)
        Me.lbProgress.Name = "lbProgress"
        Me.lbProgress.Size = New System.Drawing.Size(33, 13)
        Me.lbProgress.TabIndex = 13
        Me.lbProgress.Text = "100%"
        '
        'bgwConvert
        '
        '
        'ListView1
        '
        Me.ListView1.Columns.AddRange(New System.Windows.Forms.ColumnHeader() {Me.chRightJoystick, Me.chLeftJoystick, Me.chLeftMotors, Me.chRightMotors, Me.chRightEncoder, Me.chLeftEncoder, Me.chGyro, Me.chRightEncoderChange, Me.chLeftEncoderChange, Me.chGyroChange})
        Me.ListView1.GridLines = True
        Me.ListView1.Location = New System.Drawing.Point(15, 41)
        Me.ListView1.Name = "ListView1"
        Me.ListView1.Size = New System.Drawing.Size(1230, 204)
        Me.ListView1.TabIndex = 14
        Me.ListView1.UseCompatibleStateImageBehavior = False
        Me.ListView1.View = System.Windows.Forms.View.Details
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(12, 17)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(40, 13)
        Me.Label1.TabIndex = 15
        Me.Label1.Text = "Status:"
        '
        'lbReady
        '
        Me.lbReady.AutoSize = True
        Me.lbReady.ForeColor = System.Drawing.Color.Green
        Me.lbReady.Location = New System.Drawing.Point(58, 17)
        Me.lbReady.Name = "lbReady"
        Me.lbReady.Size = New System.Drawing.Size(94, 13)
        Me.lbReady.TabIndex = 16
        Me.lbReady.Text = "Ready To Convert"
        '
        'chRightJoystick
        '
        Me.chRightJoystick.Text = "Right Joystick"
        Me.chRightJoystick.Width = 86
        '
        'chLeftJoystick
        '
        Me.chLeftJoystick.Text = "Left Joystick"
        Me.chLeftJoystick.Width = 79
        '
        'chLeftMotors
        '
        Me.chLeftMotors.Text = "Left Motors Voltage"
        Me.chLeftMotors.Width = 111
        '
        'chRightMotors
        '
        Me.chRightMotors.Text = "Right Motors Voltage"
        Me.chRightMotors.Width = 121
        '
        'chRightEncoder
        '
        Me.chRightEncoder.Text = "Right Encoder"
        Me.chRightEncoder.Width = 99
        '
        'chLeftEncoder
        '
        Me.chLeftEncoder.Text = "Left Encoder"
        Me.chLeftEncoder.Width = 97
        '
        'chGyro
        '
        Me.chGyro.Text = "Gryo Angle"
        Me.chGyro.Width = 100
        '
        'chRightEncoderChange
        '
        Me.chRightEncoderChange.Text = "Right Encoder Change"
        Me.chRightEncoderChange.Width = 127
        '
        'chLeftEncoderChange
        '
        Me.chLeftEncoderChange.Text = "Left Encoder Change"
        Me.chLeftEncoderChange.Width = 117
        '
        'chGyroChange
        '
        Me.chGyroChange.Text = "Gyro Change"
        Me.chGyroChange.Width = 79
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(1146, 280)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(99, 23)
        Me.Button1.TabIndex = 17
        Me.Button1.Text = "Toggle Display"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'frmMain
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1253, 314)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.lbReady)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.ListView1)
        Me.Controls.Add(Me.lbProgress)
        Me.Controls.Add(Me.ProgressBar1)
        Me.Controls.Add(Me.btnStartConversion)
        Me.Controls.Add(Me.btnStartReplay)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Name = "frmMain"
        Me.Text = "Replay Data"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents btnStartReplay As System.Windows.Forms.Button
    Friend WithEvents btnStartConversion As System.Windows.Forms.Button
    Friend WithEvents ProgressBar1 As System.Windows.Forms.ProgressBar
    Friend WithEvents lbProgress As System.Windows.Forms.Label
    Friend WithEvents bgwConvert As System.ComponentModel.BackgroundWorker
    Friend WithEvents ListView1 As System.Windows.Forms.ListView
    Friend WithEvents chRightJoystick As System.Windows.Forms.ColumnHeader
    Friend WithEvents chLeftJoystick As System.Windows.Forms.ColumnHeader
    Friend WithEvents chLeftMotors As System.Windows.Forms.ColumnHeader
    Friend WithEvents chRightMotors As System.Windows.Forms.ColumnHeader
    Friend WithEvents chRightEncoder As System.Windows.Forms.ColumnHeader
    Friend WithEvents chLeftEncoder As System.Windows.Forms.ColumnHeader
    Friend WithEvents chGyro As System.Windows.Forms.ColumnHeader
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents lbReady As System.Windows.Forms.Label
    Friend WithEvents chRightEncoderChange As System.Windows.Forms.ColumnHeader
    Friend WithEvents chLeftEncoderChange As System.Windows.Forms.ColumnHeader
    Friend WithEvents chGyroChange As System.Windows.Forms.ColumnHeader
    Friend WithEvents Button1 As System.Windows.Forms.Button

End Class
