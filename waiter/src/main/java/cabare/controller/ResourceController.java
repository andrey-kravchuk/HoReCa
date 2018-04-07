package cabare.controller;

import cabare.exception.ResourceNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResourceController {

  @RequestMapping(value = {"/waiter/**"})
  public String getWaiterView(HttpServletRequest request) {
    return "waiter";
  }

  @RequestMapping(value = {"/resources/**"})
  @ResponseBody
  public byte[] getImg(HttpServletRequest request) {
    try {
      String filename = request.getRequestURI();
      String currentFolder = System.getProperty("user.dir");
      Path path = Paths.get(currentFolder, filename);
      return Files.readAllBytes(path);
    } catch (NoSuchFileException e) {
      throw new ResourceNotFoundException(e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}