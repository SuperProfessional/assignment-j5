<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail</title>
</head>
<body>
<span>Mã cơ sở: ${coSo.ma}</span>
<br/>
<span>Mã tài khoản ngân hàng: ${coSo.taiKhoanNganHang.ma}</span>
<br/>
<span>SDT cơ sở: ${coSo.soDienThoai}</span>
<br/>
<span>Ghi Chú: ${coSo.ghiChu}</span>
<br/>
<span>Trạng thái: ${coSo.trangThai ? "Hoạt động":"Ngừng hoạt động"}</span>
<br/>
<br/>
<button type="button" class="btn btn-primary"><a style="color: white" href="/co-so/hien-thi">Hiển thị</a>
</button>
</body>
</html>
