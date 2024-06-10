<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <title>Add khách hàng</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <h2>QUẢN LÝ KHÁCH HÀNG</h2>
    <div>
        <form action="/khach-hang/add" method="post">
            <div class="form-group">
                <label>ID:</label>
                <input type="text" class="form-control" value="${khachHang.id}" disabled>
            </div>
            <div class="form-group">
                <label>Mã:</label>
                <input type="text" class="form-control" name="ma" value="${khachHang.ma}">
                <small class="form-text text-danger">${messageError.ma[0]}</small>
            </div>
            <div class="form-group">
                <label>Tên:</label>
                <input type="text" class="form-control" name="ten" value="${khachHang.ten}">
                <small class="form-text text-danger">${messageError.ten[0]}</small>
            </div>
            <div class="form-group">
                <label>Tên đệm:</label>
                <input type="text" class="form-control" name="tenDem" value="${khachHang.tenDem}">
                <small class="form-text text-danger">${messageError.tenDem[0]}</small>
            </div>
            <div class="form-group">
                <label>Họ:</label>
                <input type="text" class="form-control" name="ho" value="${khachHang.ho}">
                <small class="form-text text-danger">${messageError.ho[0]}</small>
            </div>
            <div class="form-group">
                <label>Ngày sinh:</label>
                <input type="date" class="form-control" name="ngaySinh" value="${khachHang.ngaySinh}">
                <small class="form-text text-danger">${messageError.ngaySinh[0]}</small>
            </div>
            <div class="form-group">
                <label>Số điện thoại:</label>
                <input type="text" class="form-control" name="sdt" value="${khachHang.sdt}">
                <small class="form-text text-danger">${messageError.sdt[0]}</small>
            </div>
            <div class="form-group">
                <label>Địa chỉ:</label>
                <input type="text" class="form-control" name="diaChi" value="${khachHang.diaChi}">
                <small class="form-text text-danger">${messageError.diaChi[0]}</small>
            </div>
            <div class="form-group">
                <label>Thành phố:</label>
                <input type="text" class="form-control" name="thanhPho" value="${khachHang.thanhPho}">
                <small class="form-text text-danger">${messageError.thanhPho[0]}</small>
            </div>
            <div class="form-group">
                <label>Quốc gia:</label>
                <input type="text" class="form-control" name="quocGia" value="${khachHang.quocGia}">
                <small class="form-text text-danger">${messageError.quocGia[0]}</small>
            </div>
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" class="form-control" name="matKhau" value="${khachHang.matKhau}">
                <small class="form-text text-danger">${messageError.matKhau[0]}</small>
            </div>
            <div class="form-group">
                <label>Xác nhận mật khẩu:</label>
                <input type="password" class="form-control" name="confirmMatKhau" value="${khachHang.confirmMatKhau}">
                <small class="form-text text-danger">${messageError.confirmMatKhau[0]}</small>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
