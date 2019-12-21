<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "khachhang";
    $kh = $db->fetchAll("khachhang");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    	$data = [
    		"taikhoan" => postInput("taikhoan"),
    		"matkhau" => postInput("matkhau"),
    		"tenkh" => postInput("tenkh"),
    		"email" => postInput("email"),
    		"sodienthoai" => postInput("sodienthoai"),
    		"diachi" => postInput("diachi")
    		];
    	$error =[];

    	if (postInput("taikhoan")== '') {
    		$error['taikhoan'] = "Vui lòng nhập tài khoản!";
    	}
    	if (postInput("matkhau")== '') {
    		$error['matkhau'] = "Vui lòng nhập mật khẩu!";
    	}
    	if (postInput("tenkh")== '') {
    		$error['tenkh'] = "Vui lòng nhập tên!";
    	}
    	if (postInput("email")== '') {
    		$error['email'] = "Vui lòng nhập Email!";
    	}
    	if (postInput("sodienthoai")== '') {
    		$error['sodienthoai'] = "Vui lòng nhập số điện thoại!";
    	}
    	if (postInput("diachi")== '') {
    		$error['diachi'] = "Vui lòng nhập địa chỉ!";
    	}

    	if (empty($error)) {
    		$insert = $db->insert("khachhang",$data);
    		// print_r($insert);
    		if ($insert > 0) {
    			$_SESSION['success'] =  "Thêm mới thành công!";
    			redirectAdmin("khachhang");
    		} else {
    			// thêm thất bại
    			$_SESSION['error'] =  "Thêm mới thất bại!";
    			redirectAdmin("khachhang");
    		}
    	}
    }

?>

<?php require_once __DIR__."/../../layouts/hearder.php"; ?>
<div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item active">
            Home
          </li>
          <li class="breadcrumb-item active">Khách hàng</li>
          <li class="breadcrumb-item"><a href="#">Thêm khách hàng</a></li>
        </ol>
      </div>

      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn " href="add.php">Thêm mới</a></div>
          <div class="card-body">
          	<div class="card-header"><h2>Thêm mới khách hàng</h2></div>
		    <form class="form-horizontal" action="" method="POST">
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="email">Mã khách hàng:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control " id="disabledInput" placeholder="Nhập mã khách hàng" disabled>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tài khoản:</label>
		            <div class="col-sm-10">
		            	<input type="text" class="form-control" name="taikhoan" placeholder="Nhập tài khoản">
		                <?php if (isset($error['taikhoan'])): ?>
		                	<p class="text-danger"> <?php echo $error['taikhoan'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Mật khẩu</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="matkhau" placeholder="Nhập mật khẩu">
		                <?php if (isset($error['matkhau'])): ?>
		                	<p class="text-danger"> <?php echo $error['matkhau'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tên khách hàng:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="tenkh" placeholder="Nhập tên khách hàng">
		                <?php if (isset($error['tenkh'])): ?>
		                	<p class="text-danger"> <?php echo $error['tenkh'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Email:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="email" placeholder="Nhập email">
		                <?php if (isset($error['email'])): ?>
		                	<p class="text-danger"> <?php echo $error['email'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Sđt khách hàng:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="sodienthoai" placeholder="Nhập số điện thoại">
		                <?php if (isset($error['sodienthoai'])): ?>
		                	<p class="text-danger"> <?php echo $error['sodienthoai'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Địa chỉ:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="diachi" placeholder="Nhập địa chỉ">
		                <?php if (isset($error['diachi'])): ?>
		                	<p class="text-danger"> <?php echo $error['diachi'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		                <button type="submit" class="btn btn-success">Thêm</button>
		            </div>
		        </div>
		    </form>
          </div>
        </div>


<?php require_once __DIR__. "/../../layouts/footer.php"; ?>