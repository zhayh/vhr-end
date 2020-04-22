package com.niit.vhr.controller.system.basic;

import com.niit.vhr.model.Position;
import com.niit.vhr.model.RespBean;
import com.niit.vhr.service.system.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-22 15:33
 * @description :
 */

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public RespBean getAllPosition() {
        List<Position> positions = positionService.getAllPosition();
        return RespBean.ok("获取成功", positions);
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if(positionService.addPosition(position) == 1 ) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if(positionService.updatePosition(position) == 1 ) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if(positionService.deletePosition(id) == 1 ) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deletePosition(Integer[] ids) {
        if(positionService.deletePosition(ids) == ids.length) {
            return RespBean.ok("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }
}
