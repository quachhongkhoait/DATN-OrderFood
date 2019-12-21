<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "loaimonan";

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    	$data = [
    		"maloai" => postInput("maloai"),
    		"tenloai" => postInput("tenloai")
    		];
    	$error =[];

    	if (postInput("maloai")== '') {
    		$error['maloai'] = "Vui lòng nhập mã loại!";
    	}
    	if (postInput("tenloai")== '') {
    		$error['tenloai'] = "Vui lòng nhập tên loại!";
    	}

    	if (empty($error)) {
    		$insertq = $db->insert("loaimonan",$data);
    		if ($insertq > 0) {
    			$_SESSION['success'] =  "Thêm mới thành công!";
    			// redirectAdmin("loaimonan");
    		} else {
    			// thêm thất bại
    			$_SESSION['error'] =  "Thêm mới thất bại!";
    			// redirectAdmin("loaimonan");
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
          <li class="breadcrumb-item active">Loại món</li>
          <li class="breadcrumb-item"><a href="#">Thêm loại</a></li>
        </ol>
      </div>

      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn " href="add.php">Thêm loại mới</a></div>
          <div class="card-body">
          	<div class="card-header"><h2>Thêm mới loại món</h2></div>
		    <form class="form-horizontal" action="" method="POST">
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="email">Mã loại:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="maloai" placeholder="Nhập mã loại" >
		                <?php if (isset($error['maloai'])): ?>
		                	<p class="text-danger"> <?php echo $error['maloai'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tên loại:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="tenloai" placeholder="Nhập tên loại">
		                <?php if (isset($error['tenloai'])): ?>
		                	<p class="text-danger"> <?php echo $error['tenloai'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		                <button type="submit" class="btn btn-success">Thêm loại mới</button>
		            </div>
		        </div>
		    </form>
          </div>
        </div>


<?php require_once __DIR__. "/../../layouts/footer.php"; ?>