
package com.example.song.service;

import com.example.song.model.Song;
import com.example.song.repository.SongRepository;
import com.example.song.repository.SongJpaRepository;
import java.util.*;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SongJpaService implements SongRepository{

    @Autowired 
    private SongJpaRepository db; 

    
    
    @Override
    public ArrayList<Song> getSongs(){
        List<Song> p = db.findAll();
        ArrayList<Song> songs = new ArrayList<>(p);
        return songs;
    }

    @Override
    public Song addSong(Song song){
       db.save(song);
        return song;
    }

	@Override
    public Song getSong(int songId){
        try{

            Song song = db.findById(songId).get();
            return song;

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song updateSong(int songId, Song song){
        
  try{

        Song existingSong = db.findById(songId).get();
        
        if(song.getSongName() != null){
            existingSong.setSongName(song.getSongName());
        }
        if(song.getLyricist() != null){
            existingSong.setLyricist(song.getLyricist());
        }
        if(song.getSinger() != null){
            existingSong.setSinger(song.getSinger());
        }
        if(song.getMusicDirector() != null){
            existingSong.setMusicDirector(song.getMusicDirector());
        }
        db.save(existingSong);
        return getSong(songId);


  }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
      
   
    }
    @Override
    public void deleteSong(int songId){
        try{
            db.deleteById(songId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);   
        }
    }
}