package cabare.controller;

import cabare.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResourceController {

  @RequestMapping(value = {"/resources/**"})
  public byte[] getImg(HttpServletRequest request) {
    try {
      String filename = request.getRequestURI().replaceFirst("/waiter", "");
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