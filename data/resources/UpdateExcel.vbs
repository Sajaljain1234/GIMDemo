set xl=CreateObject("Excel.Application")
xl.visible=false
set wb=xl.workbooks.open("C:\Users\manish.k01\Downloads\Claim (9).xlsx")
set sh1=wb.worksheets(1)

For Each rCell in sh1.UsedRange
   On Error Resume Next
    If IsDate(rCell.value) Then
	rCell.NumberFormat="m/d/yyyy"
    End if
Next
wb.saveAs "C:\Users\manish.k01\Downloads\Claim (9)_1.xlsx"
wb.save
wb.close
xl.quit
set xl =nothing
set wb=nothing
set sh=nothing