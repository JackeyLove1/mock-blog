package com.example.mockblog.vo.params;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaParam {
    private String image;

    @NotBlank(message = "验证码Key不能为空")
    private String code;

    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 4, message = "验证码长度应该为4位")
    private String answer;
}
