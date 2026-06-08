<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ToDo List - 新規登録画面 (Mockup JSP)</title>
<!-- Google Fonts: Outfit & Noto Sans JP -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&family=Outfit:wght@300;400;500;700&display=swap"
	rel="stylesheet">

<!-- 共通の外部CSSの読み込み -->
<link rel="stylesheet" href="css/todocreate.css">
</head>
<body>
	<div class="decor-bubble bubble-1"></div>
	<div class="decor-bubble bubble-2"></div>
	
	<!-- 登録画面用に max-width を狭めたスタイルを適用 -->
	<div class="app-container container-create">
		<div class="app-content">
			<!-- ヘッダー -->
			<header>
				<div class="logo-area">
					<div class="logo-icon">
						<svg viewBox="0 0 24 24">
                            <path d="M9 11L12 14L22 4"
								stroke-linecap="round" stroke-linejoin="round" />
                            <path
								d="M21 12V19C21 19.5304 20.7893 20.0391 20.4142 20.4142C20.0391 20.7893 19.5304 21 19 21H5C4.46957 21 3.96086 20.7893 3.58579 20.4142C3.21071 20.0391 3 19.5304 3 19V5C3 4.46957 3.21071 3.96086 3.58579 3.58579C3.96086 3.21071 4.46957 3 5 3H16"
								stroke-linecap="round" stroke-linejoin="round" />
                        </svg>
					</div>
					<h1>ToDo List</h1>
				</div>
				<p>新規追加画面 (todo_create.jsp)</p>
			</header>
			<!-- ================= 新規登録画面 ================= -->
			<section id="createView">
				<!-- 
                   ※JSP/サーブレット移植時は、フォームの action に送信先サーブレットを指定します。
                   例: <form action="TodoCreate" method="post">
                -->
				<div class="form-card">
					<form method="POST"
						action="${pageContext.request.contextPath}/TodoCreate">
						<div class="form-group">
							<label class="form-label" for="txtTitle">タイトル<span>*</span></label>
							<input type="text" id="txtTitle" name="title"
								class="form-control" placeholder="ToDoのタイトルを入力（必須・最大100文字）"
								maxlength="100">
						</div>
						<div class="form-group">
							<label class="form-label" for="txtDetail">詳細</label>
							<textarea id="txtDetail" name="detail" class="form-control"
								placeholder="ToDoの詳しい説明を入力してください（任意）"></textarea>
						</div>
						<div class="form-group">
							<label class="form-label" for="selStatus">ステータス<span>*</span></label>
							<div class="select-group">
								<select id="selStatus" name="status" class="select-input">
									<option value="未着手" selected>未着手</option>
									<option value="進行中">進行中</option>
									<option value="完了">完了</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="form-label" for="txtDueDate">期限日</label> <input
								type="date" id="txtDueDate" name="due_date" class="form-control">
						</div>
						<div class="form-actions">
							<a href="${pageContext.request.contextPath}/TodoList"
								class="btn btn-secondary"> <svg width="16" height="16"
									fill="none" stroke="currentColor" stroke-width="2"
									viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round"
										d="M10 19l-7-7m0 0l7-7m-7 7h18"></path>
                </svg> 戻る
							</a>
							<button type="submit" id="btnSubmit" class="btn btn-primary">
								<svg width="16" height="16" fill="none" stroke="currentColor"
									stroke-width="2.5" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round"
										d="M5 13l4 4L19 7"></path>
                </svg>
								登録
							</button>
						</div>
					</form>
				</div>

				<!-- トースト通知 -->
				<div id="toast" class="toast">
					<div class="toast-icon">
						<svg fill="none" stroke="currentColor" stroke-width="2"
							viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round"
								d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
					</div>
					<div id="toastMsg" class="toast-msg"></div>
				</div>
</body>
</html>