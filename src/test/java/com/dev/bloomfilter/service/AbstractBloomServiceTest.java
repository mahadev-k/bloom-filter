package com.dev.bloomfilter.service;

import com.dev.bloomfilter.model.BloomState;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

public class AbstractBloomServiceTest<T>{

    protected BloomStateManager bloomStateManagerMock = Mockito.mock(BloomStateManager.class);

    protected BloomService<T> bloomService;

    protected BloomState bloomState;

}
