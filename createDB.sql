Create Database ManagementSystemDB;
GO
USE [ManagementSystemDB]
GO

CREATE TABLE [Admins](
	[ID] [char](30)  Primary key,
);

CREATE TABLE [Passwords](
	[ID] [char](30)  Primary key,
	[Password] [varchar](255) NOT NULL,

);

CREATE TABLE [Users](
	[ID] [char](30)  Primary key,
	[FirstName] [varchar](255) NOT NULL,
	[LastName] [varchar](255) NOT NULL,
	[Mail] [varchar](255) NOT NULL unique,
	[isActive] [bit] NOT NULL,
	[Roles] [varchar](255) ,
	[searchHistories] [varchar](1000) ,
	--FOREIGN KEY (ID) REFERENCES [Passwords] (ID)
	--FOREIGN KEY([ID]) REFERENCES [dbo].[Passwords] ([ID]) ON UPDATE CASCADE ON DELETE CASCADE ,
);

CREATE TABLE [Referees](
	[ID] [char](30)  Primary key,
	[Training] [varchar](50) NOT NULL,
	[Games] [varchar](255) NOT NULL,
);

CREATE TABLE [UnionRepresentatives](
	[ID] [char](30)  Primary key,
);

CREATE TABLE [Coaches](
	[ID] [char](30)  Primary key,
	[Training] [varchar](50) NOT NULL,
	[RoleInTeam] [varchar](50) NOT NULL,
	[Teams] [varchar](255) NOT NULL,
	[isActive] [bit] NOT NULL,
	[Price] [real] NOT NULL,
);

CREATE TABLE [Fans](
	[ID] [char](30)  Primary key,
	[Address] [varchar](255) NOT NULL,
	[Phone] [varchar](50) NOT NULL unique,
	[FollowedPagesIDs] [varchar](255) ,
	[ComplaintsIDs] [varchar](255) ,
	--[isMailAlerts] [bit] ,
);

CREATE TABLE [Fields](
	[ID] [char](30)  Primary key,
	[Location] [char](50) NOT NULL,
	[Name] [char](50) NOT NULL,
	[Capacity] [int] NOT NULL,
	[Teams] [varchar](255) NOT NULL,
	[isActive] [bit] NOT NULL,
	[Price] [real] NOT NULL,
	--FOREIGN KEY([Teams]) REFERENCES [dbo].[Teams] ([ID]) ON UPDATE CASCADE ON DELETE CASCADE ,
);

CREATE TABLE [Players](
	[ID] [char](30)  Primary key,
	[Birthdate] [date] NOT NULL,
	[Teams] [varchar](255) NOT NULL,
	[RoleInTeam] [varchar](255) NOT NULL,
	[isActive] [bit] NOT NULL,
	[Price] [real] NOT NULL,
	--FOREIGN KEY([Teams]) REFERENCES [dbo].[Teams] ([ID]) ON UPDATE CASCADE ON DELETE CASCADE ,
);

CREATE TABLE [TeamManagers](
	[ID] [char](30)  Primary key,
	[Teams] [varchar](255) NOT NULL,
	[isActive] [bit] NOT NULL,
	[Price] [real] NOT NULL,
	[ManageAssets] [bit] NOT NULL,
	[Finance] [bit] ,
);

CREATE TABLE [TeamOwners](
	[ID] [char](30)  Primary key,
	[Teams] [varchar](255) NOT NULL,
	[ClosedTeams] [varchar](255) ,
	[AppointedTeamOwners] [varchar] ,
	[AppointedTeamManagers] [varchar](255) ,
	[PersonalPageIDs] [varchar](255) ,
);

CREATE TABLE [PersonalPages](
	[ID] [char](30)  Primary key,
	[OwnerID] [char](30) NOT NULL unique,
	[PageData] [varchar](max) ,
	[Followers] [varchar](max) ,
);

CREATE TABLE [Teams](
	[ID] [char](30)  Primary key,
	[Name] [varchar](1000) NOT NULL,
	[Wins] [int] NOT NULL,
	[Losses] [int] NOT NULL,
	[Draws] [int] NOT NULL,
	[PersonalPageID] [varchar] (1000) NOT NULL,
	[TeamOwners] [varchar](1000) NOT NULL ,
	[TeamManagers] [varchar](1000) NOT NULL,
	[Players] [varchar](1000) NOT NULL,
	[Coaches] [varchar](1000) NOT NULL,
	[Budget] [varchar](1000) NOT NULL,
	[GamesIDs] [varchar] (1000) NOT NULL,
	[Fields] [varchar] (1000) NOT NULL,
	[LeaguesInSeasons] [varchar] (1000) NOT NULL,
	[isActive] [bit] NOT NULL,
	[isPermanentlyClosed] [bit] NOT NULL,
);

CREATE TABLE [Leagues](
	[ID] [char](30)  Primary key,
	[Name] [varchar](50) NOT NULL,
	[LeagueLevel] [varchar](50) NOT NULL,
	[LeaguesInSeasonsIDs] [varchar](255) NOT NULL,
);

CREATE TABLE [Seasons](
	[ID] [char](30)  Primary key,
	[SeasonYear] [int] NOT NULL,
	[StartDate] [date] NOT NULL,
	[LeaguesInSeasonsIDs] [varchar](255) NOT NULL,
);

CREATE TABLE [LeaguesInSeasons](
	[ID] [char](30)  Primary key,
	[AssignmentPolicy] [char](255) NOT NULL,
	[ScorePolicy] [char](255) NOT NULL,
	[GamesIDs] [varchar](255) NOT NULL,
	[RefereesIDs] [varchar](255) NOT NULL,
	[TeamsIDs] [varchar](255) NOT NULL,
	[RegistrationFee] [real] NOT NULL,
	[Records] [varchar](1000) NOT NULL,
);

CREATE TABLE [Complaints](
	[ID] [char](30)  Primary key,
	[ComplaintDate] [date] NOT NULL,
	[isActive] [bit] NOT NULL,
	[Description] [varchar] (1000)NOT NULL ,
	[ComplainedFanID] [char](50) NOT NULL,
);

CREATE TABLE [Games](
	[ID] [char](30)  Primary key,
	[GameDate] [date] NOT NULL,
	[HostScore] [int] NOT NULL,
	[GuestScore] [int] NOT NULL,
	[FieldID] [char] (50) NOT NULL,
	[MainRefereeID] [char] (50) NOT NULL,
	[SideRefereesIDs] [char] (50) NOT NULL,
	[HostTeamID] [char] (50) NOT NULL,
	[GuestTeamID] [char] (50) NOT NULL,
	[AlertsFansIDs] [varchar](max) NOT NULL,
	[EventReportID] [char] (50) NOT NULL,
	[LeagueInSeasonID] [char] (50) NOT NULL,
);

CREATE TABLE [EventReports](
	[ID] [char](30)  Primary key,
	--[GameID] [char](30) NOT NULL,
	[EventsIDs] [varchar](max) NOT NULL,
);

CREATE TABLE [OfflineUsersNotifications](
	[ID] [char](30)  Primary key,
	[Notifications] [varchar](max) NOT NULL,
);

CREATE TABLE [Events](
	[ID] [char](30)  Primary key,
	[EventType] [char](50) NOT NULL,
	[EventDate] [date] NOT NULL,
	[MinutesInGame] [real] NOT NULL,
	[Description] [varchar](max) NOT NULL,
);