/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgraphics;

public class RLRecord
{
    int saves;
    int goals;
    int assists;
    int wins;
    int gamesPlayed;
    int shots;
    long time;
    
    public RLRecord(int saves, int goals, int assists, int wins, int gamesPlayed, int shots, long time)
    {
        this.saves = saves;
        this.goals = goals;
        this.assists = assists;
        this.wins = wins;
        this.gamesPlayed = gamesPlayed;
        this.time = time;
        this.shots = shots;
    }
    
    public int getSaves()
    {
        return saves;
    }
    public int getGoals()
    {
        return saves;
    }
    public int getAssists()
    {
        return saves;
    }
    public int getWins()
    {
        return saves;
    }
    public int getGamesPlayed()
    {
        return saves;
    }
    public long getTime()
    {
        return time;
    }
}