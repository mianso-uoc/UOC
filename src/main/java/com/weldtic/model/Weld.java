package com.weldtic.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "weld")
public class Weld {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "state")
	private String state;

	@Column(name = "amp")
	@NotNull
	private Float amp;

	@Column(name = "volt")
	@NotNull
	private Float volt;

	@Column(name = "tolerance")
	@NotNull
	private int tolerance;

	@Column(name = "note")
	private String note;
	
	@ManyToOne()
    @JoinColumn(name = "piece_id")
    private Piece piece;
	
	
	
	@OneToOne(mappedBy = "weld", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	private Alarm alarm;
	
	public Weld() {}
	
	public Weld(String state, Float amp, Float volt, int tolerance, String note, Piece piece) {
		super();
		this.state = state;
		this.amp = amp;
		this.volt = volt;
		this.tolerance = tolerance;
		this.note = note;
		this.piece = piece;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Float getAmp() {
		return amp;
	}

	public void setAmp(Float amp) {
		this.amp = amp;
	}

	public Float getVolt() {
		return volt;
	}

	public void setVolt(Float volt) {
		this.volt = volt;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
}
