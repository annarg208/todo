<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ToDo List - 一覧画面 (Mockup JSP)</title>
<!-- Google Fonts: Outfit & Noto Sans JP -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&family=Outfit:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/todolist.css">

</head>
<body>
	<div class="decor-bubble bubble-1"></div>
	<div class="decor-bubble bubble-2"></div>

	<div class="app-container">
		<div class="app-content">
			<!-- ヘッダー -->
			<header>
				<div class="logo-area">
					<div class="logo-icon">
						<svg width="22" height="22" viewBox="0 0 24 24" fill="none"
							stroke="currentColor" stroke-width="2.5">
                            <path d="M9 11L12 14L22 4"
								stroke-linecap="round" stroke-linejoin="round" />
                            <path
								d="M21 12V19C21 19.5304 20.7893 20.0391 20.4142 20.4142C20.0391 20.7893 19.5304 21 19 21H5C4.46957 21 3.96086 20.7893 3.58579 20.4142C3.21071 20.0391 3 19.5304 3 19V5C3 4.46957 3.21071 3.96086 3.58579 3.58579C3.96086 3.21071 4.46957 3 5 3H16"
								stroke-linecap="round" stroke-linejoin="round" />
                        </svg>
					</div>
					<h1>ToDo List</h1>
				</div>
				<p>タスク一覧画面 (todo_list.jsp)</p>
			</header>
			<!-- ================= 一覧画面 ================= -->
			<section id="listView">
				<form method="GET"
					action="${pageContext.request.contextPath}/TodoList">
					<div class="filter-panel">
						<div class="input-group">
							<svg fill="none" stroke="currentColor" stroke-width="2"
								viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round"
									d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
            </svg>
							<input type="text" id="searchKeyword" name="keyword"
								class="search-input" placeholder="タイトルまたは詳細で検索..."
								value="${keyword}">
						</div>
						<div class="select-group">
							<select id="statusFilter" name="status" class="select-input">
								<option value="all"
									${status == null || status == 'all' ? 'selected' : ''}>すべてのステータス</option>
								<option value="未着手" ${status == '未着手' ? 'selected' : ''}>未着手</option>
								<option value="進行中" ${status == '進行中' ? 'selected' : ''}>進行中</option>
								<option value="完了" ${status == '完了' ? 'selected' : ''}>完了</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">
							<svg width="16" height="16" fill="none" stroke="currentColor"
								stroke-width="2" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round"
									d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
            </svg>
							検索
						</button>
						<a href="${pageContext.request.contextPath}/TodoList"
							class="btn btn-clear">クリア</a>
					</div>
				</form>

				<!-- テーブルコンテナ -->
				<div class="table-container">
					<table id="todoTable">
						<thead>
							<tr>
								<th class="col-id">#</th>
								<th class="col-title">タイトル</th>
								<th class="col-status">ステータス</th>
								<th class="col-date">期限日</th>
								<th class="col-actions">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="todo" items="${todos}">
								<tr>
									<td class="col-id">${todo.todoId}</td>
									<td class="col-title">${todo.title}</td>
									<td class="col-status"><c:choose>
											<c:when test="${todo.status == '未着手'}">
												<span class="badge badge-todo">${todo.status}</span>
											</c:when>
											<c:when test="${todo.status == '進行中'}">
												<span class="badge badge-doing">${todo.status}</span>
											</c:when>
											<c:otherwise>
												<span class="badge badge-done">${todo.status}</span>
											</c:otherwise>
										</c:choose></td>
									<td class="col-date">${todo.dueDate}</td>
									<td class="col-actions">
										<div class="action-cell">
											<form method="POST"
												action="${pageContext.request.contextPath}/TodoDelete">
												<input type="hidden" name="todoId" value="${todo.todoId}">
												<button type="submit" class="action-icon-btn btn-danger">
													<svg fill="none" stroke="currentColor" stroke-width="2"
														viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round"
															d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                </svg>
												</button>
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- データなし時の表示 -->
					<div id="emptyState" class="empty-state" style="display: none;">
						<svg fill="none" stroke="currentColor" stroke-width="1.5"
							viewBox="0 0 24 24">
                            <path stroke-linecap="round"
								stroke-linejoin="round"
								d="M9 12h3.75M9 15h3.375c1.08 0 1.958-.87 1.958-1.958V12a1.958 1.958 0 00-1.958-1.958H9m0 7.5h6m3-12h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zm0 2.25h.008v.008H18v-.008zM4.5 20.25h15A2.25 2.25 0 0021.75 18V6A2.25 2.25 0 0019.5 3.75h-15A2.25 2.25 0 002.25 6v12a2.25 2.25 0 002.25 2.25z"></path>
                        </svg>
						<p>該当するToDoが見つかりません</p>
					</div>
				</div>
				<!-- リスト下部アクション -->
				<div class="list-footer">
					<div class="todo-count" id="todoCount">全 0 件中 0 件を表示</div>
					<!-- 
                       ※JSP/サーブレット移植時は、aタグに変えて以下のようにサーブレットへ遷移させます。
                       例: <a href="TodoCreate" class="btn btn-primary">...
                    -->
					<a href="${pageContext.request.contextPath}/TodoCreate"
						class="btn btn-primary"> <svg width="18" height="18"
							fill="none" stroke="currentColor" stroke-width="2.5"
							viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round"
								d="M12 4.5v15m7.5-7.5h-15"></path>
    </svg> 新規追加
					</a>
				</div>
			</section>
		</div>
	</div>
	<!-- 詳細表示モーダル -->
	<div id="detailModal" class="modal-overlay">
		<div class="modal-card">
			<div class="modal-header">
				<h3>ToDo 詳細情報</h3>
				<button id="btnModalClose" class="modal-close-btn">
					<svg fill="none" stroke="currentColor" stroke-width="2"
						viewBox="0 0 24 24">
                        <path stroke-linecap="round"
							stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
                    </svg>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-row">
					<div class="modal-label">ステータス</div>
					<div id="modalStatusArea"></div>
				</div>
				<div class="modal-row">
					<div class="modal-label">タイトル</div>
					<div id="modalTitle" class="modal-val-title"></div>
				</div>
				<div class="modal-row">
					<div class="modal-label">詳細内容</div>
					<div id="modalDetail" class="modal-val-detail"></div>
				</div>
				<div class="modal-row">
					<div class="modal-label">期限日</div>
					<div id="modalDueDate" class="due-date-wrapper"></div>
				</div>
			</div>
		</div>
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