package com.michael.demo.spring.security.web;

import com.michael.demo.spring.security.model.JsonResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述:
 * <pre>
 *   Test
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/21 22:33
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t")
    public JsonResult test() {

        return JsonResult.succeed("hello spring security t");
    }

    @PreAuthorize("hasAuthority('sys:sysDictM:add')")
    @GetMapping("/t1")
    public JsonResult hello1() {

        return JsonResult.succeed("hello spring security 1");
    }

    @PreAuthorize("hasAuthority('sys:aaa:add')")
    @GetMapping("/t2")
    public JsonResult hello2() {

        return JsonResult.succeed("hello spring security 2");
    }
}
