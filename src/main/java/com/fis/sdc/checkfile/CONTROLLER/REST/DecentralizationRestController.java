package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.decentralization;
import com.fis.sdc.checkfile.SERVICE.DecentralizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/decentralization")
public class DecentralizationRestController {
    @Autowired
    DecentralizationService dao;

    @GetMapping()
    public List<decentralization> index() {

        return dao.findAll();
    }
    @GetMapping("folder/{namefolder}")
    public List<decentralization> folder(@PathVariable String namefolder) {
        return dao.findByFolder(namefolder);
    }
    @GetMapping("user")
    public List<decentralization> user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return dao.findByUser(auth.getName());
    }
    @GetMapping("group/{folder}/{iddr}/{groupname}")
    public List<decentralization> findgroup(@PathVariable String folder,@PathVariable int iddr,@PathVariable String groupname) {
        return dao.findgroup(folder, iddr, groupname);
    }
    @GetMapping("alldata/{folder}/{iddr}/{username}")
    public List<decentralization> data(@PathVariable String folder,@PathVariable int iddr,@PathVariable String username) {
        return dao.findbigdata(folder, iddr, username);
    }

    @GetMapping("{idprm}")
    public decentralization all(@PathVariable long idprm) {
        return dao.findOne(idprm);
    }

    @PostMapping()
    public int save(@RequestBody decentralization de) {
        return dao.insert(de.getGroupname(),de.getFolder(), de.getPer(), de.getUser(), de.getFile());
    }
    @PutMapping()
    public decentralization update(@RequestBody decentralization de) {
        return dao.create(de);
    }
    @DeleteMapping("/{iddr}")
    public void delete(@PathVariable("iddr") long iddr) {
        dao.deleteOne(iddr);
    }

    @GetMapping("dulieu/{folder}/{iddr}")
    public List<decentralization> dulieu(@PathVariable("folder") String folder,@PathVariable("iddr") int iddr) {
        return dao.findByfolder(folder,iddr);
    }

    @GetMapping("groupname/{iddr}")
    public List<decentralization> dulieu(@PathVariable("iddr") int iddr) {
        return dao.findgroupname(iddr);
    }

    @GetMapping("findgroup/{idprm}")
    public List<String> groupname(@PathVariable("idprm") int idprm) {
        return dao.findgroupby(idprm);
    }
    @GetMapping("user/{idprm}")
    public List<decentralization> username(@PathVariable int idprm) {
        return dao.usernameprm(idprm);
    }
    @PostMapping("check/{idprm}")
    public void index(@PathVariable int idprm,@RequestBody String data){
        dao.DeleteByGroup(idprm,data);
    }
    @PostMapping("checkuser/{idprm}")
    public void usercheck(@PathVariable int idprm,@RequestBody Account data){
        dao.DeleteByUsername(idprm,data);
    }
    @GetMapping("/usernamgroup/{idprm}")
    public List<decentralization> user(@PathVariable("idprm") int idprm) {
        return dao.usernameprm(idprm);
    }


}
