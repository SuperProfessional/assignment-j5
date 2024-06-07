<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <title>Add CƠ SỞ</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <h2>QUẢN LÝ CƠ SỞ</h2>
    <div>
        <form action="/co-so/add" method="post">
            <div class="form-group">
                <label>Mã phòng ban:</label>
                <input type="text" class="form-control" name="ma" value="${coSo.ma}">
                <small class="form-text text-danger">${messageError.ma[0]}</small>
            </div>
            <div class="form-group">
                <label>Mã tài khoản ngân hàng:</label>
                <select name="taiKhoanNganHangId" class="form-control">
                    <c:forEach items="${taiKhoanNganHangList}" var="nh">
                        <option value="${nh.id}">${nh.ma}</option>
                    </c:forEach>
                </select>
                <small class="form-text text-danger">${messageError.taiKhoanNganHang[0]}</small>
            </div>
            <div class="form-group">
                <label>Số điện thoại cơ sở:</label>
                <input type="text" class="form-control" name="soDienThoai" value="${coSo.soDienThoai}">
                <small class="form-text text-danger">${messageError.soDienThoai[0]}</small>
            </div>
            <div class="form-group">
                <label>Ghi chú:</label>
                <input type="text" class="form-control" name="ghiChu" value="${coSo.ghiChu}">
                <small class="form-text text-danger">${messageError.ghiChu[0]}</small>
            </div>
            <div class="form-group">
                <label>Trạng thái:</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai" id="exampleRadios1" value="true" ${coSo.trangThai ? "checked" : ""}>
                    <label class="form-check-label" for="exampleRadios1">
                        Hoạt động
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai" id="exampleRadios2" value="false" ${!coSo.trangThai ? "checked" : ""}>
                    <label class="form-check-label" for="exampleRadios2">
                        Ngừng hoạt động
                    </label>
                </div>
                <small class="form-text text-danger">${messageError.trangThai[0]}</small>
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
