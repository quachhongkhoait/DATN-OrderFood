<?php
    require_once __DIR__."/../autoload/autoload.php";
?>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Admin - Orderfood</title>

  <!-- Custom fonts for this template-->
  <link href="<?php echo base_url() ?>/public/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="<?php echo base_url() ?>/public/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<?php echo base_url() ?>/public/admin/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

  <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="<?php echo base_url() ?>/"><?php echo $_SESSION['admin_name']; ?></a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i>
    </button>
    <!-- Navbar -->
    <ul class="nav navbar-nav ml-auto" >
      <li class="nav-item dropdown no-arrow">
        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user-circle fa-fw"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
          <a class="dropdown-item" href="#">Settings</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
        </div>
      </li>
    </ul>

  </nav>

  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
      <li class="nav-item <?php echo isset($open) && $open == 'home' ? 'active' : ' ' ?>">
        <a class="nav-link" href="<?php echo base_url() ?>">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span>
        </a>
      </li>
      <li class="nav-item <?php echo isset($open) && $open == 'khachhang' ? 'active' : ' ' ?>">
        <a class="nav-link" href="<?php echo modules("khachhang") ?>">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Danh sách khách hàng</span></a>
      </li>
      <li class="nav-item <?php echo isset($open) && $open == 'quanan' ? 'active' : ' ' ?>">
        <a class="nav-link" href="<?php echo modules("quanan") ?>">
          <i class="fa fa-list"></i>
          <span>Danh sách quán</span>
        </a>
      </li>
      <li class="nav-item <?php echo isset($open) && $open == 'loaimonan' ? 'active' : ' ' ?>">
        <a class="nav-link" href="<?php echo modules("loaimonan") ?>">
          <i class="fas fa-fw fa-table"></i>
          <span>Loại món ăn</span></a>
      </li>
      </li>
      <li class="nav-item <?php echo isset($open) && $open == 'monan' ? 'active' : ' ' ?>">
        <a class="nav-link" href="<?php echo modules("monan") ?>">
          <i class="fa fa-list"></i>
          <span> Món ăn</span>
        </a>
    </ul>