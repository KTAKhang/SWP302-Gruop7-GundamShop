<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống Kê Doanh Thu</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/css/ewstyle.css">
</head>

<body>
    <div class="container-fluid d-flex p-0">
        <!-- Sidebar -->
        <jsp:include page="../layout/navbar.jsp" />

        <!-- Main Content -->
        <div class="main-content p-0">
            <jsp:include page="../layout/header.jsp" />

            <div class="p-4">
                <h1 class="mb-4 mt-4 text-center" style="font-weight: bold;">Thống Kê Doanh Thu</h1>

                <!-- Breadcrumb -->
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item active">Statistics</li>
                </ol>

                <!-- Dropdown chọn năm -->
                <div class="form-group mb-4">
                    <label for="yearSelection" class="form-label">Chọn Năm:</label>
                    <select id="yearSelection" class="form-control" onchange="changeYear()">
                        <c:forEach var="y" items="${yearsWithData}">
                            <option value="${y}" <c:if test="${y == selectedYear}">selected</c:if>>${y}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Bảng dữ liệu doanh thu -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle text-center">
                        <thead>
                            <tr>
                                <th>Tháng</th>
                                <th>Doanh thu (VNĐ)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${monthlyRevenue}">
                                <tr>
                                    <td>Tháng ${entry.key}</td>
                                    <td>${entry.value}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Biểu đồ thống kê doanh thu -->
                <div id="chart_div" style="width: 100%; height: 400px; margin-top: 20px;"></div>
            </div>
        </div>
    </div>

    <!-- Google Charts Script -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Tháng', 'Doanh thu (VNĐ)'],
                <c:forEach var="entry" items="${monthlyRevenue}">
                    ['Tháng ${entry.key}', ${entry.value}],
                </c:forEach>
            ]);

            var options = {
                title: 'Thống Kê Doanh Thu Theo Tháng',
                hAxis: {title: 'Tháng'},
                vAxis: {title: 'Doanh thu (VNĐ)'},
                legend: { position: 'none' },
                colors: ['#00c6ff'],
                backgroundColor: 'transparent'
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }

        function changeYear() {
            const selectedYear = document.getElementById("yearSelection").value;
            window.location.href = `/admin?year=${selectedYear}`;
        }
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
