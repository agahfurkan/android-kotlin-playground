package com.agah.furkan.core.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.reflect.Field

class ValidatorUtilTest {
    @Test
    fun `if there is only one field and it is null validator returns invalid with errormessage`() {
        val dummyModel = DummyModel(null)

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result[0] is ValidationResult.Invalid).isTrue()
        assertThat((result[0] as ValidationResult.Invalid).errorMessage).isNotEmpty()
    }

    @Test
    fun `if there is only one field and it is not null validator returns valid`() {
        val dummyModel = DummyModel("test")

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result[0] is ValidationResult.Valid).isTrue()
    }

    @Test
    fun `if there is only one field and it is empty validator returns invalid with errormessage`() {
        val dummyModel = DummyModel("")

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result[0] is ValidationResult.Invalid).isTrue()
        assertThat((result[0] as ValidationResult.Invalid).errorMessage).isNotEmpty()
    }

    @Test
    fun `if there are 5 fields and 3 of them null, validator response should contain 2 valid and 3 invalid item`() {
        val dummyModel = DummyModel2("test", "test", null, null, null)

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result.size).isEqualTo(5)
        assertThat(result.filterIsInstance<ValidationResult.Valid>().size).isEqualTo(2)
        assertThat(result.filterIsInstance<ValidationResult.Invalid>().size).isEqualTo(3)
    }

    @Test
    fun `if there are 5 fields and 5 of them null, validator response should contain 5 invalid item`() {
        val dummyModel = DummyModel2(null, null, null, null, null)

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result.size).isEqualTo(5)
        assertThat(result.filterIsInstance<ValidationResult.Valid>()).isEmpty()
        assertThat(result.filterIsInstance<ValidationResult.Invalid>().size).isEqualTo(5)
    }

    @Test
    fun `if model contains 5 field and 2 of them have validator annotation, validator response should have 2 item`() {
        val dummyModel = DummyModel3("test", "test", null, null, null)

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun `if model contains 5 field and 3 of them have validator annotation, validator response should have 3 item`() {
        val dummyModel = DummyModel4("test", "test", null, null, null)

        val result = ValidatorUtil.validate(dummyModel)
        assertThat(result.size).isEqualTo(3)
    }
}

class DummyValidator : Validator<String> {
    override var errorMessage: String? = null

    override fun validate(field: Field, value: String?): Boolean {
        return if (value.isNullOrEmpty()) {
            errorMessage = "value is null or empty"
            false
        } else {
            true
        }
    }
}

class DummyValidator2 : Validator<String> {
    override var errorMessage: String? = "error-message"

    override fun validate(field: Field, value: String?): Boolean {
        return false
    }
}

data class DummyModel(@Validate(DummyValidator::class) val value: String?)
data class DummyModel2(
    @Validate(DummyValidator::class) val value1: String?,
    @Validate(DummyValidator::class) val value2: String?,
    @Validate(DummyValidator::class) val value3: String?,
    @Validate(DummyValidator::class) val value4: String?,
    @Validate(DummyValidator::class) val value5: String?
)

data class DummyModel3(
    @Validate(DummyValidator::class) val value1: String?,
    @Validate(DummyValidator::class) val value2: String?,
    val value3: String?,
    val value4: String?,
    val value5: String?
)

data class DummyModel4(
    @Validate(DummyValidator::class) val value1: String?,
    @Validate(DummyValidator::class) val value2: String?,
    @Validate(DummyValidator2::class) val value3: String?,
    val value4: String?,
    val value5: String?
)