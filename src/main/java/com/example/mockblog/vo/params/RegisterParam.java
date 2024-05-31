package com.example.mockblog.vo.params;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {
    @NotBlank(message = "账号不能为空")
    @Pattern(
            regexp = "^(1[3-9]\\d{9}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "账户应该为手机号或者有效邮箱地址"
    )
    private String account;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码长度最少8位最多不超过20位")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "密码中必须包含大小写字母和特殊字符(@#$%^&+=)")
    private String password;

    @NotBlank(message = "请再次输入密码")
    private String rePassword;

    @NotBlank(message = "验证码Key不能为空")
    private String code;

    @NotBlank(message = "验证码不能为空")
    private String image;
}
