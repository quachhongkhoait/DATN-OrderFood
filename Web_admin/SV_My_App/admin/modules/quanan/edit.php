<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "quanan";
    $ma_qa = intval(getInput("ma_qa"));
    $quan_an = $db->fetchID("quan_an","ma_qa",$ma_qa);
    if (empty($quan_an)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("quanan");
    }

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    	$data = [
    		"ten_qa" => postInput("ten_qa"),
    		"hinhanh_qa" => postInput("hinhanh_qa"),
    		"diachi_qa" => postInput("diachi_qa"),
    		"thoigianphucvu" => postInput("thoigianphucvu"),
    		];
    	$error =[];

    	if (postInput("ten_qa")== '') {
    		$error['ten_qa'] = "Vui lòng nhập tên quán!";
    	}
    	if (postInput("hinhanh_qa")== '') {
    		$error['hinhanh_qa'] = "Vui lòng nhập url!";
    	}
    	if (postInput("diachi_qa")== '') {
    		$error['diachi_qa'] = "Vui lòng nhập địa chỉ!";
    	}
    	if (postInput("thoigianphucvu")== '') {
    		$error['thoigianphucvu'] = "Vui lòng nhập thời gian!";
    	}
    	if (empty($error)) {
    		$updata1 = $db->update("quan_an",$data,array("ma_qa"=>$ma_qa));
    		// print_r($insert);
    		if ($updata1 > 0) {
    			$_SESSION['success'] =  "Cập nhật thành công!";
    			redirectAdmin("quanan");
    		} else {
    			// thêm thất bại
    			$_SESSION['error'] =  "Cập nhật thất bại!";
    			redirectAdmin("quanan");
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
          <li class="breadcrumb-item active">Danh sách quán</li>
          <li class="breadcrumb-item"><a href="#">Sửa quán</a></li>
        </ol>
      </div>

      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn " href="add.php">Sửa quán</a></div>
          <div class="card-body">
          	<div class="card-header"><h2>Sửa quán ăn</h2></div>
		    <form class="form-horizontal" action="" method="POST">
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="email">Mã quán ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control " id="disabledInput" placeholder="Nhập mã quán ăn" disabled>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tên quán ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" value="<?php echo $quan_an['ten_qa'] ?>" name="ten_qa" placeholder="Nhập tên quán ăn">
		                <?php if (isset($error['ten_qa'])): ?>
		                	<p class="text-danger"> <?php echo $error['ten_qa'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Địa chỉ quán ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" value="<?php echo $quan_an['diachi_qa'] ?>" name="diachi_qa" placeholder="Nhập địa chỉ quán">
		                <?php if (isset($error['diachi_qa'])): ?>
		                	<p class="text-danger"> <?php echo $error['diachi_qa'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Thời gian phục vụ:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" value="<?php echo $quan_an['thoigianphucvu'] ?>" name="thoigianphucvu" placeholder="Nhập thời gian phục vụ">
		                <?php if (isset($error['thoigianphucvu'])): ?>
		                	<p class="text-danger"> <?php echo $error['thoigianphucvu'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Hình ảnh quán ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" value="<?php echo $quan_an['hinhanh_qa'] ?>" name="hinhanh_qa" placeholder="Nhập url quán ăn">
		                <?php if (isset($error['hinhanh_qa'])): ?>
		                	<p class="text-danger"> <?php echo $error['hinhanh_qa'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		                <button type="submit" class="btn btn-success">Thêm quán</button>
		            </div>
		        </div>
		    </form>
          </div>
        </div>


<?php require_once __DIR__. "/../../layouts/footer.php"; ?>