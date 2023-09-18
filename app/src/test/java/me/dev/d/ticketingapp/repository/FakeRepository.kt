package me.dev.d.ticketingapp.repository

open class FakeRepository {
    protected var errorCase: Boolean = false
    protected var statusCode: Int = 200
    protected var apiErrorCode: String = ""
    protected var errorException: Exception? = null

    /**
     *  Make sure to call [resetCase] method after you are done with this. Otherwise it may result
     *  in failure of other test cases
     */
    fun prepareForError(
        statusCode: Int = 400,
        apiErrorCode: String = "",
        exception: Exception? = null
    ) {
        errorCase = true
        this.statusCode = statusCode
        this.apiErrorCode = apiErrorCode
        if (exception != null) {
            errorException = exception
        }
    }

    fun resetCase() {
        prepareForSuccess()
    }

    private fun prepareForSuccess() {
        errorCase = false
        statusCode = 200
        apiErrorCode = ""
        errorException = null
    }
}