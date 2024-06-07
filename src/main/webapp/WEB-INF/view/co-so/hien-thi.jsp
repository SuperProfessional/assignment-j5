<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <title>Hiển Thị Cơ Sở</title>
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
    <div class="mt-2">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Mã cơ sở</th>
                <th scope="col">Mã tài khoản ngân hàng</th>
                <th scope="col">SDT cơ sở</th>
                <th scope="col">Ghi Chú</th>
                <th scope="col">Trạng thái</th>
                <th colspan="3">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cs" items="${coSoPage.content}" varStatus="var">
                <tr>
                    <th scope="row">${var.index + 1}</th>
                    <td>${cs.ma}</td>
                    <td>${cs.taiKhoanNganHang.ma}</td>
                    <td>${cs.soDienThoai}</td>
                    <td>${cs.ghiChu}</td>
                    <td>${cs.trangThai ? "Hoạt động":"Ngừng hoạt động"}</td>
                    <td>
                        <button type="button" class="btn btn-primary"><a style="color: white"
                                                                         href="/co-so/view-update/${cs.id}">Update</a>
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger"><a style="color: white"
                                                                        href="/co-so/remove/${cs.id}">Remove</a>
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger"><a style="color: white"
                                                                        href="/co-so/detail/${cs.id}">Detail</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/co-so/hien-thi?pageNumber=${coSoPage.first ? coSoPage.totalPages : coSoPage.number}">Previous</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${coSoPage.totalPages}">
                        <li class="page-item"><a class="page-link" href="/co-so/hien-thi?pageNumber=${i}">${i}</a></li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="/co-so/hien-thi?pageNumber=${coSoPage.last ? 1 : coSoPage.number + 2}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
        <button type="button" class="btn btn-success"><a style="color: white" href="/co-so/view-add">Add</a></button>
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
