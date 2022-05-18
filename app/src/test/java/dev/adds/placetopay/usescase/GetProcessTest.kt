package dev.adds.placetopay.usescase

import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.model.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.AssertionError


internal class GetProcessTest{
    @RelaxedMockK
    private lateinit var repository: PaymentRepository
    @RelaxedMockK
    private lateinit var processItem: ProcessItem
    lateinit var getProcess: GetProcess

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getProcess = GetProcess(repository)
    }
    private fun mockProcessResponseItem() = ProcessResponseItem(
        StatusItem("APPROVED","00","Aprobada","2022-04-20T21:07:22-05:00"),
        "CREDIBANCO",3, "1234567890","ID_VS",
        AmountItem("USD",98),"999999"
    )
    private fun mockProcessItem() = ProcessItem(
        PayerItem("Ms. Nelle Beahan DVM","Spencer",
            "gsteuber@kling.com","CC",
            "3154383838","3006108300"
        ),
        PaymentItem("TESTTING","Lorem",
            AmountItem("USD",98)
        ),
        InstrumentItem(CardItem("36545400000008","12/26","123",0))
    )

    @Test
    fun `when the API response is successfully`() = runBlocking {

        coEvery { repository.getProcessResponse(processItem) } returns mockProcessResponseItem()

        val response = getProcess(processItem)


        coVerify(exactly = 1) { repository.getProcessResponse(processItem) }
        coVerify(exactly = 1) { repository.register(any()) }

        assert(mockProcessResponseItem().statusItem.status == response.statusItem.status)
    }

}