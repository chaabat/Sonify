package com.sonify.controller;

import com.sonify.dto.AlbumDTO;
import com.sonify.service.interfaces.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    // User endpoints
    @GetMapping("/api/user/albums")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumDTO>> getAllAlbums(
            @PageableDefault(sort = "titre", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(albumService.getAllAlbums(pageable));
    }

    @GetMapping("/api/user/albums/search/titre")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumDTO>> searchAlbumsByTitle(
            @RequestParam String titre,
            @PageableDefault(sort = "titre", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(albumService.searchAlbumsByTitle(titre, pageable));
    }

    @GetMapping("/api/user/albums/search/artiste")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumDTO>> searchAlbumsByArtist(
            @RequestParam String artiste,
            @PageableDefault(sort = "titre", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(albumService.searchAlbumsByArtist(artiste, pageable));
    }

    @GetMapping("/api/user/albums/filter/annee")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumDTO>> filterAlbumsByYear(
            @RequestParam Integer annee,
            @PageableDefault(sort = "titre", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(albumService.filterAlbumsByYear(annee, pageable));
    }

    // Admin endpoints
    @PostMapping("/api/admin/albums")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.createAlbum(albumDTO));
    }

    @PutMapping("/api/admin/albums/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumDTO> updateAlbum(
            @PathVariable String id,
            @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(id, albumDTO));
    }

    @DeleteMapping("/api/admin/albums/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }
}
