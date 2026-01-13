package dev.jh.shared

/**
 * 모든 API 응답에서 공통으로 사용하는 래퍼 모델이다.
 *
 * - data: 실제 응답 데이터
 * - message: 사용자에게 전달할 메시지
 * - success: 성공 여부
 */
data class ApiResponse<T>(
	val data: T,
	val message: String,
	val success: Boolean
) {
	companion object {
		/**
		 * 성공 응답을 간단히 생성한다.
		 */
		fun <T> success(data: T, message: String = "OK"): ApiResponse<T> {
			return ApiResponse(
				data = data,
				message = message,
				success = true
			)
		}
	}
}
