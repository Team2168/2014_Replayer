<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class DataViewer
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
        Dim GaugeCircularScale1 As DevComponents.Instrumentation.GaugeCircularScale = New DevComponents.Instrumentation.GaugeCircularScale()
        Dim GaugePointer1 As DevComponents.Instrumentation.GaugePointer = New DevComponents.Instrumentation.GaugePointer()
        Dim GaugeRange1 As DevComponents.Instrumentation.GaugeRange = New DevComponents.Instrumentation.GaugeRange()
        Dim GaugeSection1 As DevComponents.Instrumentation.GaugeSection = New DevComponents.Instrumentation.GaugeSection()
        Dim GradientFillColor1 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GradientFillColor2 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GaugeText1 As DevComponents.Instrumentation.GaugeText = New DevComponents.Instrumentation.GaugeText()
        Dim GaugeCircularScale2 As DevComponents.Instrumentation.GaugeCircularScale = New DevComponents.Instrumentation.GaugeCircularScale()
        Dim GaugePointer2 As DevComponents.Instrumentation.GaugePointer = New DevComponents.Instrumentation.GaugePointer()
        Dim GaugeRange2 As DevComponents.Instrumentation.GaugeRange = New DevComponents.Instrumentation.GaugeRange()
        Dim GaugeSection2 As DevComponents.Instrumentation.GaugeSection = New DevComponents.Instrumentation.GaugeSection()
        Dim GradientFillColor3 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GradientFillColor4 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GaugeText2 As DevComponents.Instrumentation.GaugeText = New DevComponents.Instrumentation.GaugeText()
        Dim GaugeCircularScale3 As DevComponents.Instrumentation.GaugeCircularScale = New DevComponents.Instrumentation.GaugeCircularScale()
        Dim GaugePointer3 As DevComponents.Instrumentation.GaugePointer = New DevComponents.Instrumentation.GaugePointer()
        Dim GradientFillColor5 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GradientFillColor6 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GaugeText3 As DevComponents.Instrumentation.GaugeText = New DevComponents.Instrumentation.GaugeText()
        Dim GradientFillColor7 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GradientFillColor8 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GaugeLinearScale1 As DevComponents.Instrumentation.GaugeLinearScale = New DevComponents.Instrumentation.GaugeLinearScale()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(DataViewer))
        Dim GaugePointer4 As DevComponents.Instrumentation.GaugePointer = New DevComponents.Instrumentation.GaugePointer()
        Dim GaugeSection3 As DevComponents.Instrumentation.GaugeSection = New DevComponents.Instrumentation.GaugeSection()
        Dim GradientFillColor9 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GradientFillColor10 As DevComponents.Instrumentation.GradientFillColor = New DevComponents.Instrumentation.GradientFillColor()
        Dim GaugeLinearScale2 As DevComponents.Instrumentation.GaugeLinearScale = New DevComponents.Instrumentation.GaugeLinearScale()
        Dim GaugePointer5 As DevComponents.Instrumentation.GaugePointer = New DevComponents.Instrumentation.GaugePointer()
        Dim GaugeSection4 As DevComponents.Instrumentation.GaugeSection = New DevComponents.Instrumentation.GaugeSection()
        Me.bgwDrawElements = New System.ComponentModel.BackgroundWorker()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Button2 = New System.Windows.Forms.Button()
        Me.lbCurrentTime = New System.Windows.Forms.Label()
        Me.gcRightMotor = New DevComponents.Instrumentation.GaugeControl()
        Me.gcLeftMotor = New DevComponents.Instrumentation.GaugeControl()
        Me.GaugeControl1 = New DevComponents.Instrumentation.GaugeControl()
        Me.gcLeftSpeed = New DevComponents.Instrumentation.GaugeControl()
        Me.gcRightSpeed = New DevComponents.Instrumentation.GaugeControl()
        CType(Me.gcRightMotor, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.gcLeftMotor, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.GaugeControl1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.gcLeftSpeed, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.gcRightSpeed, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'bgwDrawElements
        '
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(12, 12)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(38, 23)
        Me.Button1.TabIndex = 0
        Me.Button1.Text = "Start"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Button2
        '
        Me.Button2.Location = New System.Drawing.Point(56, 12)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(45, 23)
        Me.Button2.TabIndex = 1
        Me.Button2.Text = "Pause"
        Me.Button2.UseVisualStyleBackColor = True
        '
        'lbCurrentTime
        '
        Me.lbCurrentTime.AutoSize = True
        Me.lbCurrentTime.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lbCurrentTime.Location = New System.Drawing.Point(629, 12)
        Me.lbCurrentTime.Name = "lbCurrentTime"
        Me.lbCurrentTime.Size = New System.Drawing.Size(55, 24)
        Me.lbCurrentTime.TabIndex = 4
        Me.lbCurrentTime.Text = "00:00"
        '
        'gcRightMotor
        '
        GaugeCircularScale1.MajorTickMarks.Interval = 0.5R
        GaugeCircularScale1.MaxPin.Name = "MaxPin"
        GaugeCircularScale1.MaxValue = 1.0R
        GaugeCircularScale1.MinorTickMarks.Interval = 0.1R
        GaugeCircularScale1.MinPin.Name = "MinPin"
        GaugeCircularScale1.MinValue = -1.0R
        GaugeCircularScale1.Name = "Scale1"
        GaugePointer1.CapFillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer1.CapFillColor.BorderWidth = 1
        GaugePointer1.CapFillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer1.CapFillColor.Color2 = System.Drawing.Color.DimGray
        GaugePointer1.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer1.FillColor.BorderWidth = 1
        GaugePointer1.FillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer1.FillColor.Color2 = System.Drawing.Color.Red
        GaugePointer1.Name = "Pointer1"
        GaugePointer1.Style = DevComponents.Instrumentation.PointerStyle.Needle
        GaugePointer1.ThermoBackColor.BorderColor = System.Drawing.Color.Black
        GaugePointer1.ThermoBackColor.BorderWidth = 1
        GaugePointer1.ThermoBackColor.Color1 = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer))
        GaugeCircularScale1.Pointers.AddRange(New DevComponents.Instrumentation.GaugePointer() {GaugePointer1})
        GaugeRange1.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugeRange1.FillColor.BorderWidth = 1
        GaugeRange1.FillColor.Color1 = System.Drawing.Color.Lime
        GaugeRange1.FillColor.Color2 = System.Drawing.Color.Red
        GaugeRange1.Name = "Range1"
        GaugeRange1.ScaleOffset = 0.28!
        GaugeRange1.StartValue = 70.0R
        GaugeCircularScale1.Ranges.AddRange(New DevComponents.Instrumentation.GaugeRange() {GaugeRange1})
        GaugeSection1.FillColor.Color1 = System.Drawing.Color.CornflowerBlue
        GaugeSection1.FillColor.Color2 = System.Drawing.Color.Purple
        GaugeSection1.Name = "Section1"
        GaugeCircularScale1.Sections.AddRange(New DevComponents.Instrumentation.GaugeSection() {GaugeSection1})
        Me.gcRightMotor.CircularScales.AddRange(New DevComponents.Instrumentation.GaugeCircularScale() {GaugeCircularScale1})
        GradientFillColor1.Color1 = System.Drawing.Color.Gainsboro
        GradientFillColor1.Color2 = System.Drawing.Color.DarkGray
        Me.gcRightMotor.Frame.BackColor = GradientFillColor1
        GradientFillColor2.BorderColor = System.Drawing.Color.Gainsboro
        GradientFillColor2.BorderWidth = 1
        GradientFillColor2.Color1 = System.Drawing.Color.White
        GradientFillColor2.Color2 = System.Drawing.Color.DimGray
        Me.gcRightMotor.Frame.FrameColor = GradientFillColor2
        Me.gcRightMotor.Frame.OuterBevel = 0.15!
        Me.gcRightMotor.Frame.RoundRectangleArc = 0.0!
        GaugeText1.BackColor.BorderColor = System.Drawing.Color.Black
        GaugeText1.Name = "Motor Value"
        GaugeText1.Text = "Right Motor"
        Me.gcRightMotor.GaugeItems.AddRange(New DevComponents.Instrumentation.GaugeItem() {GaugeText1})
        Me.gcRightMotor.Location = New System.Drawing.Point(434, 431)
        Me.gcRightMotor.Name = "gcRightMotor"
        Me.gcRightMotor.Size = New System.Drawing.Size(250, 250)
        Me.gcRightMotor.TabIndex = 5
        Me.gcRightMotor.Text = "GaugeControl1"
        '
        'gcLeftMotor
        '
        GaugeCircularScale2.MajorTickMarks.Interval = 0.5R
        GaugeCircularScale2.MaxPin.Name = "MaxPin"
        GaugeCircularScale2.MaxValue = 1.0R
        GaugeCircularScale2.MinorTickMarks.Interval = 0.1R
        GaugeCircularScale2.MinPin.Name = "MinPin"
        GaugeCircularScale2.MinValue = -1.0R
        GaugeCircularScale2.Name = "Scale1"
        GaugePointer2.CapFillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer2.CapFillColor.BorderWidth = 1
        GaugePointer2.CapFillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer2.CapFillColor.Color2 = System.Drawing.Color.DimGray
        GaugePointer2.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer2.FillColor.BorderWidth = 1
        GaugePointer2.FillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer2.FillColor.Color2 = System.Drawing.Color.Red
        GaugePointer2.Name = "Pointer1"
        GaugePointer2.Style = DevComponents.Instrumentation.PointerStyle.Needle
        GaugePointer2.ThermoBackColor.BorderColor = System.Drawing.Color.Black
        GaugePointer2.ThermoBackColor.BorderWidth = 1
        GaugePointer2.ThermoBackColor.Color1 = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer))
        GaugeCircularScale2.Pointers.AddRange(New DevComponents.Instrumentation.GaugePointer() {GaugePointer2})
        GaugeRange2.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugeRange2.FillColor.BorderWidth = 1
        GaugeRange2.FillColor.Color1 = System.Drawing.Color.Lime
        GaugeRange2.FillColor.Color2 = System.Drawing.Color.Red
        GaugeRange2.Name = "Range1"
        GaugeRange2.ScaleOffset = 0.28!
        GaugeRange2.StartValue = 70.0R
        GaugeCircularScale2.Ranges.AddRange(New DevComponents.Instrumentation.GaugeRange() {GaugeRange2})
        GaugeSection2.FillColor.Color1 = System.Drawing.Color.CornflowerBlue
        GaugeSection2.FillColor.Color2 = System.Drawing.Color.Purple
        GaugeSection2.Name = "Section1"
        GaugeCircularScale2.Sections.AddRange(New DevComponents.Instrumentation.GaugeSection() {GaugeSection2})
        Me.gcLeftMotor.CircularScales.AddRange(New DevComponents.Instrumentation.GaugeCircularScale() {GaugeCircularScale2})
        GradientFillColor3.Color1 = System.Drawing.Color.Gainsboro
        GradientFillColor3.Color2 = System.Drawing.Color.DarkGray
        Me.gcLeftMotor.Frame.BackColor = GradientFillColor3
        GradientFillColor4.BorderColor = System.Drawing.Color.Gainsboro
        GradientFillColor4.BorderWidth = 1
        GradientFillColor4.Color1 = System.Drawing.Color.White
        GradientFillColor4.Color2 = System.Drawing.Color.DimGray
        Me.gcLeftMotor.Frame.FrameColor = GradientFillColor4
        Me.gcLeftMotor.Frame.OuterBevel = 0.15!
        Me.gcLeftMotor.Frame.RoundRectangleArc = 0.0!
        GaugeText2.BackColor.BorderColor = System.Drawing.Color.Black
        GaugeText2.Name = "Motor Value"
        GaugeText2.Text = "Right Motor"
        Me.gcLeftMotor.GaugeItems.AddRange(New DevComponents.Instrumentation.GaugeItem() {GaugeText2})
        Me.gcLeftMotor.Location = New System.Drawing.Point(12, 431)
        Me.gcLeftMotor.Name = "gcLeftMotor"
        Me.gcLeftMotor.Size = New System.Drawing.Size(250, 250)
        Me.gcLeftMotor.TabIndex = 6
        Me.gcLeftMotor.Text = "GaugeControl2"
        '
        'GaugeControl1
        '
        GaugeCircularScale3.MajorTickMarks.Interval = 90.0R
        GaugeCircularScale3.MaxLimit = 360.0R
        GaugeCircularScale3.MaxPin.Name = "MaxPin"
        GaugeCircularScale3.MaxValue = 360.0R
        GaugeCircularScale3.MinLimit = 0.0R
        GaugeCircularScale3.MinorTickMarks.Interval = 15.0R
        GaugeCircularScale3.MinPin.Name = "MinPin"
        GaugeCircularScale3.Name = "Scale1"
        GaugePointer3.CapFillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer3.CapFillColor.BorderWidth = 1
        GaugePointer3.CapFillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer3.CapFillColor.Color2 = System.Drawing.Color.DimGray
        GaugePointer3.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer3.FillColor.BorderWidth = 1
        GaugePointer3.FillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer3.FillColor.Color2 = System.Drawing.Color.Red
        GaugePointer3.Name = "Pointer1"
        GaugePointer3.Style = DevComponents.Instrumentation.PointerStyle.Needle
        GaugePointer3.ThermoBackColor.BorderColor = System.Drawing.Color.Black
        GaugePointer3.ThermoBackColor.BorderWidth = 1
        GaugePointer3.ThermoBackColor.Color1 = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer))
        GaugeCircularScale3.Pointers.AddRange(New DevComponents.Instrumentation.GaugePointer() {GaugePointer3})
        GaugeCircularScale3.Radius = 0.395!
        GaugeCircularScale3.StartAngle = 270.0!
        GaugeCircularScale3.SweepAngle = 360.0!
        GaugeCircularScale3.Width = 0.0!
        Me.GaugeControl1.CircularScales.AddRange(New DevComponents.Instrumentation.GaugeCircularScale() {GaugeCircularScale3})
        GradientFillColor5.Color1 = System.Drawing.Color.Gainsboro
        GradientFillColor5.Color2 = System.Drawing.Color.DarkGray
        Me.GaugeControl1.Frame.BackColor = GradientFillColor5
        GradientFillColor6.BorderColor = System.Drawing.Color.Gainsboro
        GradientFillColor6.BorderWidth = 1
        GradientFillColor6.Color1 = System.Drawing.Color.White
        GradientFillColor6.Color2 = System.Drawing.Color.DimGray
        Me.GaugeControl1.Frame.FrameColor = GradientFillColor6
        Me.GaugeControl1.Frame.OuterBevel = 0.15!
        Me.GaugeControl1.Frame.RoundRectangleArc = 0.0!
        GaugeText3.BackColor.BorderColor = System.Drawing.Color.Black
        GaugeText3.Name = "Motor Value"
        GaugeText3.Text = "Right Motor"
        Me.GaugeControl1.GaugeItems.AddRange(New DevComponents.Instrumentation.GaugeItem() {GaugeText3})
        Me.GaugeControl1.Location = New System.Drawing.Point(270, 549)
        Me.GaugeControl1.Name = "GaugeControl1"
        Me.GaugeControl1.Size = New System.Drawing.Size(164, 132)
        Me.GaugeControl1.TabIndex = 7
        Me.GaugeControl1.Text = "GaugeControl2"
        '
        'gcLeftSpeed
        '
        GradientFillColor7.Color1 = System.Drawing.Color.Gainsboro
        GradientFillColor7.Color2 = System.Drawing.Color.DarkGray
        Me.gcLeftSpeed.Frame.BackColor = GradientFillColor7
        GradientFillColor8.BorderColor = System.Drawing.Color.Gainsboro
        GradientFillColor8.BorderWidth = 1
        GradientFillColor8.Color1 = System.Drawing.Color.White
        GradientFillColor8.Color2 = System.Drawing.Color.DimGray
        Me.gcLeftSpeed.Frame.FrameColor = GradientFillColor8
        GaugeLinearScale1.Labels.Layout.ScaleOffset = 0.03!
        GaugeLinearScale1.Location = CType(resources.GetObject("GaugeLinearScale1.Location"), System.Drawing.PointF)
        GaugeLinearScale1.MaxPin.Name = "MaxPin"
        GaugeLinearScale1.MaxPin.Visible = False
        GaugeLinearScale1.MaxValue = 15.0R
        GaugeLinearScale1.MinPin.Name = "MinPin"
        GaugeLinearScale1.MinPin.Visible = False
        GaugeLinearScale1.Name = "Scale1"
        GaugeLinearScale1.Orientation = System.Windows.Forms.Orientation.Vertical
        GaugePointer4.CapFillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer4.CapFillColor.BorderWidth = 1
        GaugePointer4.CapFillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer4.CapFillColor.Color2 = System.Drawing.Color.DimGray
        GaugePointer4.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer4.FillColor.BorderWidth = 1
        GaugePointer4.FillColor.Color1 = System.Drawing.Color.Red
        GaugePointer4.Name = "Pointer1"
        GaugePointer4.Placement = DevComponents.Instrumentation.DisplayPlacement.Far
        GaugePointer4.ScaleOffset = 0.05!
        GaugePointer4.ThermoBackColor.BorderColor = System.Drawing.Color.Black
        GaugePointer4.ThermoBackColor.BorderWidth = 1
        GaugePointer4.ThermoBackColor.Color1 = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer))
        GaugeLinearScale1.Pointers.AddRange(New DevComponents.Instrumentation.GaugePointer() {GaugePointer4})
        GaugeSection3.FillColor.Color1 = System.Drawing.Color.CornflowerBlue
        GaugeSection3.FillColor.Color2 = System.Drawing.Color.Purple
        GaugeSection3.Name = "Section1"
        GaugeLinearScale1.Sections.AddRange(New DevComponents.Instrumentation.GaugeSection() {GaugeSection3})
        Me.gcLeftSpeed.LinearScales.AddRange(New DevComponents.Instrumentation.GaugeLinearScale() {GaugeLinearScale1})
        Me.gcLeftSpeed.Location = New System.Drawing.Point(2, 114)
        Me.gcLeftSpeed.Name = "gcLeftSpeed"
        Me.gcLeftSpeed.Size = New System.Drawing.Size(87, 353)
        Me.gcLeftSpeed.TabIndex = 8
        Me.gcLeftSpeed.Text = "GaugeControl2"
        '
        'gcRightSpeed
        '
        GradientFillColor9.Color1 = System.Drawing.Color.Gainsboro
        GradientFillColor9.Color2 = System.Drawing.Color.DarkGray
        Me.gcRightSpeed.Frame.BackColor = GradientFillColor9
        GradientFillColor10.BorderColor = System.Drawing.Color.Gainsboro
        GradientFillColor10.BorderWidth = 1
        GradientFillColor10.Color1 = System.Drawing.Color.White
        GradientFillColor10.Color2 = System.Drawing.Color.DimGray
        Me.gcRightSpeed.Frame.FrameColor = GradientFillColor10
        GaugeLinearScale2.Labels.Layout.ScaleOffset = 0.03!
        GaugeLinearScale2.Location = CType(resources.GetObject("GaugeLinearScale2.Location"), System.Drawing.PointF)
        GaugeLinearScale2.MaxPin.Name = "MaxPin"
        GaugeLinearScale2.MaxPin.Visible = False
        GaugeLinearScale2.MaxValue = 50.0R
        GaugeLinearScale2.MinPin.Name = "MinPin"
        GaugeLinearScale2.MinPin.Visible = False
        GaugeLinearScale2.Name = "Scale1"
        GaugeLinearScale2.Orientation = System.Windows.Forms.Orientation.Vertical
        GaugePointer5.CapFillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer5.CapFillColor.BorderWidth = 1
        GaugePointer5.CapFillColor.Color1 = System.Drawing.Color.WhiteSmoke
        GaugePointer5.CapFillColor.Color2 = System.Drawing.Color.DimGray
        GaugePointer5.FillColor.BorderColor = System.Drawing.Color.DimGray
        GaugePointer5.FillColor.BorderWidth = 1
        GaugePointer5.FillColor.Color1 = System.Drawing.Color.Red
        GaugePointer5.Name = "Pointer1"
        GaugePointer5.Placement = DevComponents.Instrumentation.DisplayPlacement.Far
        GaugePointer5.ScaleOffset = 0.05!
        GaugePointer5.ThermoBackColor.BorderColor = System.Drawing.Color.Black
        GaugePointer5.ThermoBackColor.BorderWidth = 1
        GaugePointer5.ThermoBackColor.Color1 = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer), CType(CType(60, Byte), Integer))
        GaugeLinearScale2.Pointers.AddRange(New DevComponents.Instrumentation.GaugePointer() {GaugePointer5})
        GaugeSection4.FillColor.Color1 = System.Drawing.Color.CornflowerBlue
        GaugeSection4.FillColor.Color2 = System.Drawing.Color.Purple
        GaugeSection4.Name = "Section1"
        GaugeLinearScale2.Sections.AddRange(New DevComponents.Instrumentation.GaugeSection() {GaugeSection4})
        Me.gcRightSpeed.LinearScales.AddRange(New DevComponents.Instrumentation.GaugeLinearScale() {GaugeLinearScale2})
        Me.gcRightSpeed.Location = New System.Drawing.Point(597, 114)
        Me.gcRightSpeed.Name = "gcRightSpeed"
        Me.gcRightSpeed.Size = New System.Drawing.Size(87, 353)
        Me.gcRightSpeed.TabIndex = 9
        Me.gcRightSpeed.Text = "GaugeControl3"
        '
        'DataViewer
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(696, 693)
        Me.Controls.Add(Me.gcRightSpeed)
        Me.Controls.Add(Me.gcLeftSpeed)
        Me.Controls.Add(Me.GaugeControl1)
        Me.Controls.Add(Me.gcLeftMotor)
        Me.Controls.Add(Me.gcRightMotor)
        Me.Controls.Add(Me.lbCurrentTime)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.Button1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Name = "DataViewer"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Data Viewer"
        CType(Me.gcRightMotor, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.gcLeftMotor, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.GaugeControl1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.gcLeftSpeed, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.gcRightSpeed, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents bgwDrawElements As System.ComponentModel.BackgroundWorker
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents Button2 As System.Windows.Forms.Button
    Friend WithEvents lbCurrentTime As System.Windows.Forms.Label
    Friend WithEvents gcRightMotor As DevComponents.Instrumentation.GaugeControl
    Friend WithEvents gcLeftMotor As DevComponents.Instrumentation.GaugeControl
    Friend WithEvents GaugeControl1 As DevComponents.Instrumentation.GaugeControl
    Friend WithEvents gcLeftSpeed As DevComponents.Instrumentation.GaugeControl
    Friend WithEvents gcRightSpeed As DevComponents.Instrumentation.GaugeControl
End Class
