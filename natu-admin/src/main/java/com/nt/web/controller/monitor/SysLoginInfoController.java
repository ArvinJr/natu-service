package com.nt.web.controller.monitor;

import com.nt.common.annotation.Log;
import com.nt.common.core.controller.BaseController;
import com.nt.common.core.domain.AjaxResult;
import com.nt.common.core.page.TableDataInfo;
import com.nt.common.enums.BusinessType;
import com.nt.common.utils.poi.ExcelUtil;
import com.nt.system.domain.SysLoginInfo;
import com.nt.system.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author 唐僧
 */
@RestController
@RequestMapping("/monitor/loginInfo")
public class SysLoginInfoController extends BaseController {
    @Autowired
    private ISysLoginInfoService loginInfoService;

    @PreAuthorize("@ss.hasPermi('monitor:loginInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfo loginInfo) {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:loginInfo:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLoginInfo loginInfo) {
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        ExcelUtil<SysLoginInfo> util = new ExcelUtil<SysLoginInfo>(SysLoginInfo.class);
        util.exportExcel(response, list, "登录日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:loginInfo:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:loginInfo:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        loginInfoService.cleanLoginInfo();
        return AjaxResult.success();
    }
}
