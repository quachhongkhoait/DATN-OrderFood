<?php
    require_once __DIR__."/autoload/autoload.php";
    $open = "home";

    $quanan_count = $db->countTable("ma_qa","quan_an");
    $monan_count = $db->countTable("mamonan","monan");
    $donhang_count = $db->countTable("status","don_dathang WHERE status = 1");
    $choduyet_count = $db->countTable("status","don_dathang WHERE status = 0");


    $qr ="SELECT don_dathang.*,khachhang.tenkh as namekh ,khachhang.sodienthoai as sdtkh FROM don_dathang,khachhang WHERE don_dathang.ma_kh = khachhang.ma_kh";
    //SELECT * FROM don_dathang,khachhang WHERE don_dathang.ma_kh = khachhang.ma_kh


    $dondh = $db->fetchsql($qr);

?>
<?php require_once __DIR__."/layouts/hearder.php"; ?>

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <div>Xin chào <?php echo $_SESSION['admin_name']; ?></div>

            <!-- <a href="<?php echo base_url() ?>">Home</a> -->
          </li>
          <!-- <li class="breadcrumb-item active">Bảng món ăn</li> -->
        </ol>
      <!-- /.container-fluid -->
      <?php require_once __DIR__."/partials/notification.php"; ?>
<div class="row">
    <!-- thông báo đơn hàng cần duyệt -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-comments"></i>
                </div>
                <div class="mr-5">Đơn hàng chờ duyệt</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5><?php echo $choduyet_count; ?> Đơn</h5></span>
            </div>
        </div>
    </div>    
    <!-- quán -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                </div>
                <div class="mr-5">
                    <span>Số quán đang hợp tác</span>
                </div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5><?php echo $quanan_count; ?> Quán</h5></span>
            </div>
            <!-- <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left"></span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
            </a> -->
        </div>
    </div>
    <!-- đơn hàng đã duyệt -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-shopping-cart"></i>
                </div>
                <div class="mr-5">Đơn hàng đã duyệt</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5><?php echo $donhang_count; ?> Đơn</h5></span>
            </div>
            <!-- <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left">View Details</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
            </a> -->
        </div>
    </div>
    <!-- tài khoản block -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <div class="mr-5">Tài khoản đang block</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5>50</h5></span>
            </div>
        </div>
    </div>
    <!-- tổng món ăn -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-info o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <div class="mr-5">Tổng số món ăn</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5><?php echo $monan_count; ?> món đang bán</h5></span>
            </div>
        </div>
    </div>
    <!-- doang thu theo ngày -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <div class="mr-5">Doanh thu theo ngày</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5>400.000 đ</h5></span>
            </div>
        </div>
    </div>
    <!-- doang thu theo tháng -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <div class="mr-5">Doanh thu theo tháng</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5>500.000.000 đ</h5></span>
            </div>
        </div>
    </div>
    <!-- doang thu theo năm -->
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <div class="mr-5">Tổng doanh thu</div>
            </div>
            <div class="card-footer text-white clearfix">
                <span><h5>2.900.000.000 đ</h5></span>
            </div>
        </div>
    </div>

    <div class="container">
  <h3>Đơn đặt hàng</h3>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>#</th>
        <th>Tên khách hàng</th>
        <th>Số điện thoại</th>
        <th>Tổng tiền</th>
        <th>Trạng thái</th>
        <th>Ghi chú</th>
      </tr>
    </thead>
    <tbody>
        <?php foreach ($dondh as $item): ?>
      <tr>
        <td><?php echo $item['ma_dondh']?></td>
        <td><?php echo $item['namekh']?></td>
        <td>0<?php echo $item['sdtkh']?></td>
        <td><?php echo number_format($item['tong_gia']);?> đ</td>
        <td>
            <?php if($item['status']== 0): ?>
                <a href="status.php?ma_dondh=<?php echo $item['ma_dondh']?>" class="btn btn-xs text-white <?php echo "btn-danger" ?>">Chưa xử lý</a>
            <?php else: ?>
                <a href="status.php?ma_dondh=<?php echo $item['ma_dondh']?>" class="btn btn-xs text-white <?php echo "btn-info" ?>">Đã xử lý</a>
            <?php endif ?>
        </td>
        <td><?php echo $item['ghi_chu']?></td>
      </tr>
      <?php endforeach ?>
    </tbody>
  </table>
</div>
</div>
</div>
<?php require_once __DIR__. "/layouts/footer.php"; ?>