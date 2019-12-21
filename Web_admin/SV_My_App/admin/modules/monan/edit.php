<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "monan";
    $loaimonan = $db->fetchAll("loaimonan");
    $quan_an = $db->fetchAll("quan_an");
    $mamonan = intval(getInput("mamonan"));
    $where = $mamonan." AND monan.ma_qa = quan_an.ma_qa AND monan.maloai = loaimonan.maloai";
    $edit_monan = $db->fetchID("monan,quan_an,loaimonan","mamonan",$where);
    if (empty($edit_monan)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("monan");
    }


    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    	$data = [
    		"maloai" => postInput("maloai"),
    		"ma_qa" => postInput("ma_qa"),
    		"tenmonan" => postInput("tenmonan"),
    		"gia" => postInput("gia"),
    		"hinhanhmonan" => postInput("hinhanhmonan")
    		];
    	$error =[];

    	if (postInput("tenmonan")== '') {
    		$error['tenmonan'] = "Vui lòng nhập tên món!";
    	}
    	if (empty($error)) {
    		$updata1 = $db->update("monan",$data,array("mamonan"=>$mamonan));
    		// print_r($insert);
    		if ($updata1 > 0) {
    			$_SESSION['success'] =  "Cập nhật thành công!";
    			redirectAdmin("monan");
    		} else {
    			// thêm thất bại
    			$_SESSION['error'] =  "Cập nhật thất bại!";
    			redirectAdmin("monan");
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
          <li class="breadcrumb-item active">Món ăn</li>
          <li class="breadcrumb-item"><a href="#">Sửa món</a></li>
        </ol>
      </div>

      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn " href="add.php">Sửa món</a></div>
          <div class="card-body">
          	<div class="card-header"><h2>Thêm mới món ăn</h2></div>
		    <form class="form-horizontal" action="" method="POST">
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="email">Mã món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control " placeholder="Nhập mã món ăn" value="<?php echo $edit_monan['mamonan'] ?>" disabled>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Loại món ăn:</label>
		            <div class="col-sm-10">
		            	<select class="form-control" name="maloai" >
		            		<option value="<?php echo $edit_monan['maloai'] ?>"><?php echo $edit_monan['tenloai'] ?></option>
		            		<!-- <?php echo $edit_monan['tenloai'] ?> -->
		            		<?php foreach ($loaimonan as $item): ?>
		            		<option value="<?php echo $item['maloai'] ?>"><?php echo $item['tenloai'] ?></option>
		            		<?php endforeach ?>
		            	</select>
		                <!-- <input type="text" class="form-control" name="maloai" placeholder="Nhập mã loại" value="<?php echo $edit_monan['maloai'] ?>"> -->
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Quán ăn:</label>
		            <div class="col-sm-10">
		            	<select class="form-control" name="ma_qa" >
		            		<option value="<?php echo $edit_monan['ma_qa'] ?>"><?php echo $edit_monan['ten_qa'] ?></option>
		            		<!-- <?php echo $edit_monan['tenloai'] ?> -->
		            		<?php foreach ($quan_an as $item): ?>
		            		<option value="<?php echo $item['ma_qa'] ?>"><?php echo $item['ten_qa'] ?></option>
		            		<?php endforeach ?>
		            	</select>
		                <!-- <input type="text" class="form-control" name="ma_qa" placeholder="Nhập mã quán ăn" value="<?php echo $edit_monan['ma_qa'] ?>"> -->
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tên món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="tenmonan" placeholder="Nhập tên món ăn" value="<?php echo $edit_monan['tenmonan'] ?>">
		                <?php if (isset($error['tenmonan'])): ?>
		                	<p class="text-danger"> <?php echo $error['tenmonan'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Giá món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="gia" placeholder="Nhập giá món ăn" value="<?php echo $edit_monan['gia'] ?>">
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Hình ảnh món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="hinhanhmonan" placeholder="Nhập url ảnh món ăn" value="<?php echo $edit_monan['hinhanhmonan'] ?>">
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
<?php require_once __DIR__. "/../../layouts/footer.php";