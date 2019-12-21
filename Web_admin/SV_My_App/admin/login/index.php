<?php
  // require_once __DIR__."/library/dbconnect.php";
   function base_url1()
    {
        return $url ="http://localhost:8888/SV_My_App/admin";
    }
    session_start();
    require_once __DIR__. "/../library/database.php";
    require_once __DIR__. "/../library/function.php";
    $db = new database;

    $data = [
        "taikhoan" => postInput("taikhoan"),
        "matkhau" => postInput("matkhau")
        ];
      $error =[];


    if ($_SERVER["REQUEST_METHOD"] == "POST") {
      if ($data['taikhoan']== '') {
        $error['taikhoan'] = "Tài khoản không được để trống!";
      }
      if ($data['matkhau']== '') {
        $error['matkhau'] = "Mật khẩu không được để trống!";
      }

      if (empty($error)) {
        $is_check = $db->fetchOne("admin","taikhoan = '".$data["taikhoan"]."' AND matkhau = '".$data["matkhau"]."'");
        if ($is_check != null) {
          $_SESSION['admin_name'] = $is_check["ten"];
          $_SESSION['admin_id'] = $is_check["id"];
          echo "<script>location.href='/SV_My_App/admin/'</script>;";
          // header("location: ".base_url1()."/13123/");exit();
        } else {
          $_SESSION['error'] = "Đăng nhập thất bại!";
        }
      }
    }
 ?>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Orderfood - Login</title>

  <!-- Custom fonts for this template-->
  <link href="<?php echo base_url1() ?>/public/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="<?php echo base_url1() ?>/public/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<?php echo base_url1() ?>/public/admin/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark" style="background-image: url(https://media.licdn.com/dms/image/C4E12AQHO0-NzzwJodQ/article-inline_image-shrink_1500_2232/0?e=1580947200&v=beta&t=HC57cOBiK7zW_YMsNhBtgQmwkMannm8nFB2hhKuQABQ); ">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Đăng nhập</div>
      <div class="card-body">
        <form action='' method='POST'>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="inputEmail" name="taikhoan" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Tài khoản</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="password" id="inputPassword" name="matkhau" class="form-control" placeholder="Password" required="required">
              <label for="inputPassword">Mật khẩu</label>
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me">
                Ghi nhớ mật khẩu
              </label>
            </div>
          </div>
          <input type="submit" name="login" value="Đăng nhập" class="btn btn-primary btn-block" />
        </form>
        <div class="text-center">
          <!-- <a class="d-block small mt-3" href="register.html">Register an Account</a> -->
          <a class="d-block small mt-3" href="forgot-password.php">Quên mật khẩu?</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="<?php echo base_url1() ?>/public/admin/vendor/jquery/jquery.min.js"></script>
  <script src="<?php echo base_url1() ?>/public/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<?php echo base_url1() ?>/public/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="<?php echo base_url1() ?>/public/admin/vendor/datatables/jquery.dataTables.js"></script>
  <script src="<?php echo base_url1() ?>/../public/admin/vendor/datatables/dataTables.bootstrap4.js"></script>
  <script src="<?php echo base_url1() ?>/public/admin/js/sb-admin.min.js"></script>
  <script src="<?php echo base_url1() ?>/public/admin/js/demo/datatables-demo.js"></script>

</body>

</html>
