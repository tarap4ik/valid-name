package com.org.test.validatenametest.dto;

import java.io.Serializable;

public record RequestContentDTO(long requestId, String content) implements Serializable {
}
