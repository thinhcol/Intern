package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Favorite;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FavoriteService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestController {
    @Autowired
    FileService fileService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<Favorite> listfav() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return favoriteService.checkuser(auth.getName());
    }
    @GetMapping  ("/folder/{username}/{folder}")
    public List<Favorite> findfolder(@PathVariable("username") String username,@PathVariable("folder") String folder) {
        return favoriteService.findByfolder(folder,username);
    }

    @GetMapping  ("/filefavorite")
    public List<File> filefavote() {
        return favoriteService.filefavorite();
    }

    @GetMapping  ("/folderfavorite")
    public List<FileZile> folderfavote() {
        return favoriteService.folderfavorite();
    }
    @DeleteMapping ("/dlt/{username}/{folder}")
    public void folder(@PathVariable("username") String username,@PathVariable("folder") String folder) {
        favoriteService.DeleteByUsIf(username,folder);
    }

    @PostMapping()
    public Favorite save(@RequestBody Favorite favorite) {
        return favoriteService.save(favorite);
    }

    @DeleteMapping("/{IDFV}")
    public void delete(@PathVariable("IDFV") long IDFV) {
        favoriteService.deleteById(IDFV);
    }

    @GetMapping("/check")
    public boolean check(@RequestParam long id, @RequestParam String u) {
        if (favoriteService.checksao(u, id) != null) {
            return true;
        } else {
            return false;
        }
    }
//    @DeleteMapping ("/delete")
//    public void delete(@RequestParam long id, @RequestParam String u) {
//       favoriteService.DeleteByUsIf(u,id);
//    }
}
