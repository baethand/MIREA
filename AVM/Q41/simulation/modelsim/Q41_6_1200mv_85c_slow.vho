-- Copyright (C) 1991-2013 Altera Corporation
-- Your use of Altera Corporation's design tools, logic functions 
-- and other software and tools, and its AMPP partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Altera Program License 
-- Subscription Agreement, Altera MegaCore Function License 
-- Agreement, or other applicable license agreement, including, 
-- without limitation, that your use is for the sole purpose of 
-- programming logic devices manufactured by Altera and sold by 
-- Altera or its authorized distributors.  Please refer to the 
-- applicable agreement for further details.

-- VENDOR "Altera"
-- PROGRAM "Quartus II 64-Bit"
-- VERSION "Version 13.1.0 Build 162 10/23/2013 SJ Web Edition"

-- DATE "11/05/2023 21:50:14"

-- 
-- Device: Altera EP4CGX15BF14C6 Package FBGA169
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA;
LIBRARY CYCLONEIV;
LIBRARY IEEE;
USE ALTERA.ALTERA_PRIMITIVES_COMPONENTS.ALL;
USE CYCLONEIV.CYCLONEIV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	Q41 IS
    PORT (
	q : OUT std_logic_vector(3 DOWNTO 0);
	clock : IN std_logic;
	b : IN std_logic;
	a : IN std_logic
	);
END Q41;

-- Design Ports Information
-- q[3]	=>  Location: PIN_N6,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[2]	=>  Location: PIN_L4,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[1]	=>  Location: PIN_M6,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[0]	=>  Location: PIN_N4,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- b	=>  Location: PIN_L5,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- clock	=>  Location: PIN_J7,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- a	=>  Location: PIN_L7,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF Q41 IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_q : std_logic_vector(3 DOWNTO 0);
SIGNAL ww_clock : std_logic;
SIGNAL ww_b : std_logic;
SIGNAL ww_a : std_logic;
SIGNAL \clock~inputclkctrl_INCLK_bus\ : std_logic_vector(3 DOWNTO 0);
SIGNAL \q[3]~output_o\ : std_logic;
SIGNAL \q[2]~output_o\ : std_logic;
SIGNAL \q[1]~output_o\ : std_logic;
SIGNAL \q[0]~output_o\ : std_logic;
SIGNAL \clock~input_o\ : std_logic;
SIGNAL \clock~inputclkctrl_outclk\ : std_logic;
SIGNAL \b~input_o\ : std_logic;
SIGNAL \inst~1_combout\ : std_logic;
SIGNAL \inst23~0_combout\ : std_logic;
SIGNAL \inst23~1_combout\ : std_logic;
SIGNAL \inst33~q\ : std_logic;
SIGNAL \a~input_o\ : std_logic;
SIGNAL \inst18~0_combout\ : std_logic;
SIGNAL \inst18~1_combout\ : std_logic;
SIGNAL \inst~2_combout\ : std_logic;
SIGNAL \inst24~2_combout\ : std_logic;
SIGNAL \inst24~3_combout\ : std_logic;
SIGNAL \inst24~4_combout\ : std_logic;
SIGNAL \inst31~q\ : std_logic;
SIGNAL \inst24~5_combout\ : std_logic;
SIGNAL \inst25~1_combout\ : std_logic;
SIGNAL \inst25~0_combout\ : std_logic;
SIGNAL \inst25~2_combout\ : std_logic;
SIGNAL \inst29~q\ : std_logic;
SIGNAL \inst~0_combout\ : std_logic;
SIGNAL \inst26~5_combout\ : std_logic;
SIGNAL \inst26~6_combout\ : std_logic;
SIGNAL \inst26~4_combout\ : std_logic;
SIGNAL \inst27~q\ : std_logic;

BEGIN

q <= ww_q;
ww_clock <= clock;
ww_b <= b;
ww_a <= a;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

\clock~inputclkctrl_INCLK_bus\ <= (vcc & vcc & vcc & \clock~input_o\);

-- Location: IOOBUF_X12_Y0_N2
\q[3]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \inst27~q\,
	devoe => ww_devoe,
	o => \q[3]~output_o\);

-- Location: IOOBUF_X8_Y0_N9
\q[2]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \inst29~q\,
	devoe => ww_devoe,
	o => \q[2]~output_o\);

-- Location: IOOBUF_X12_Y0_N9
\q[1]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \inst31~q\,
	devoe => ww_devoe,
	o => \q[1]~output_o\);

-- Location: IOOBUF_X10_Y0_N9
\q[0]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \inst33~q\,
	devoe => ww_devoe,
	o => \q[0]~output_o\);

-- Location: IOIBUF_X16_Y0_N15
\clock~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_clock,
	o => \clock~input_o\);

-- Location: CLKCTRL_G17
\clock~inputclkctrl\ : cycloneiv_clkctrl
-- pragma translate_off
GENERIC MAP (
	clock_type => "global clock",
	ena_register_mode => "none")
-- pragma translate_on
PORT MAP (
	inclk => \clock~inputclkctrl_INCLK_bus\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	outclk => \clock~inputclkctrl_outclk\);

-- Location: IOIBUF_X14_Y0_N8
\b~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_b,
	o => \b~input_o\);

-- Location: LCCOMB_X13_Y1_N6
\inst~1\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst~1_combout\ = (!\inst33~q\ & (!\inst27~q\ & (\inst29~q\ & !\inst31~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000000000010000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst33~q\,
	datab => \inst27~q\,
	datac => \inst29~q\,
	datad => \inst31~q\,
	combout => \inst~1_combout\);

-- Location: LCCOMB_X13_Y1_N16
\inst23~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst23~0_combout\ = (\inst29~q\ & ((\inst33~q\ & (!\inst27~q\ & \inst31~q\)) # (!\inst33~q\ & ((\inst31~q\) # (!\inst27~q\))))) # (!\inst29~q\ & ((\inst33~q\ & (\inst27~q\)) # (!\inst33~q\ & (!\inst27~q\ & \inst31~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0110101101000010",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst33~q\,
	datac => \inst27~q\,
	datad => \inst31~q\,
	combout => \inst23~0_combout\);

-- Location: LCCOMB_X13_Y1_N18
\inst23~1\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst23~1_combout\ = ((!\b~input_o\ & ((\inst~0_combout\) # (\inst~1_combout\)))) # (!\inst23~0_combout\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0101010011111111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \b~input_o\,
	datab => \inst~0_combout\,
	datac => \inst~1_combout\,
	datad => \inst23~0_combout\,
	combout => \inst23~1_combout\);

-- Location: FF_X13_Y1_N19
inst33 : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \inst23~1_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \inst33~q\);

-- Location: IOIBUF_X14_Y0_N1
\a~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_a,
	o => \a~input_o\);

-- Location: LCCOMB_X13_Y1_N20
\inst18~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst18~0_combout\ = (\a~input_o\ & \inst31~q\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111000000000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datac => \a~input_o\,
	datad => \inst31~q\,
	combout => \inst18~0_combout\);

-- Location: LCCOMB_X13_Y1_N14
\inst18~1\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst18~1_combout\ = (\inst29~q\ & (\inst33~q\ & (!\inst27~q\ & \inst18~0_combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000100000000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst33~q\,
	datac => \inst27~q\,
	datad => \inst18~0_combout\,
	combout => \inst18~1_combout\);

-- Location: LCCOMB_X12_Y1_N16
\inst~2\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst~2_combout\ = (!\inst27~q\ & (\inst33~q\ & (!\inst29~q\ & \inst31~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000010000000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst27~q\,
	datab => \inst33~q\,
	datac => \inst29~q\,
	datad => \inst31~q\,
	combout => \inst~2_combout\);

-- Location: LCCOMB_X13_Y1_N4
\inst24~2\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst24~2_combout\ = (\inst31~q\) # (\inst33~q\ $ (\inst27~q\))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111111100111100",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \inst33~q\,
	datac => \inst27~q\,
	datad => \inst31~q\,
	combout => \inst24~2_combout\);

-- Location: LCCOMB_X13_Y1_N2
\inst24~3\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst24~3_combout\ = (\inst29~q\ & (((!\inst~1_combout\)) # (!\b~input_o\))) # (!\inst29~q\ & (\inst24~2_combout\ & ((!\inst~1_combout\) # (!\b~input_o\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0011001011111010",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \b~input_o\,
	datac => \inst24~2_combout\,
	datad => \inst~1_combout\,
	combout => \inst24~3_combout\);

-- Location: LCCOMB_X13_Y1_N24
\inst24~4\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst24~4_combout\ = ((\inst18~1_combout\) # ((\inst~2_combout\) # (!\inst24~3_combout\))) # (!\inst24~5_combout\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111110111111111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst24~5_combout\,
	datab => \inst18~1_combout\,
	datac => \inst~2_combout\,
	datad => \inst24~3_combout\,
	combout => \inst24~4_combout\);

-- Location: FF_X13_Y1_N25
inst31 : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \inst24~4_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \inst31~q\);

-- Location: LCCOMB_X13_Y1_N12
\inst24~5\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst24~5_combout\ = (\inst31~q\ & (((\inst29~q\) # (\inst33~q\)))) # (!\inst31~q\ & ((\inst29~q\ $ (\inst33~q\)) # (!\inst27~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1101111111110001",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst27~q\,
	datab => \inst31~q\,
	datac => \inst29~q\,
	datad => \inst33~q\,
	combout => \inst24~5_combout\);

-- Location: LCCOMB_X13_Y1_N30
\inst25~1\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst25~1_combout\ = (\inst29~q\ & ((\inst31~q\ & (\inst27~q\ $ (!\inst33~q\))) # (!\inst31~q\ & (\inst27~q\ & !\inst33~q\)))) # (!\inst29~q\ & (!\inst31~q\ & (!\inst27~q\ & \inst33~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1000000100101000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst31~q\,
	datac => \inst27~q\,
	datad => \inst33~q\,
	combout => \inst25~1_combout\);

-- Location: LCCOMB_X13_Y1_N0
\inst25~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst25~0_combout\ = (\b~input_o\ & (\inst~0_combout\)) # (!\b~input_o\ & ((\inst~1_combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1100111111000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \inst~0_combout\,
	datac => \b~input_o\,
	datad => \inst~1_combout\,
	combout => \inst25~0_combout\);

-- Location: LCCOMB_X13_Y1_N26
\inst25~2\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst25~2_combout\ = ((\inst18~1_combout\) # ((\inst25~1_combout\) # (\inst25~0_combout\))) # (!\inst24~5_combout\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111111111111101",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst24~5_combout\,
	datab => \inst18~1_combout\,
	datac => \inst25~1_combout\,
	datad => \inst25~0_combout\,
	combout => \inst25~2_combout\);

-- Location: FF_X13_Y1_N27
inst29 : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \inst25~2_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \inst29~q\);

-- Location: LCCOMB_X13_Y1_N28
\inst~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst~0_combout\ = (!\inst29~q\ & (\inst33~q\ & (\inst27~q\ & \inst31~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0100000000000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst33~q\,
	datac => \inst27~q\,
	datad => \inst31~q\,
	combout => \inst~0_combout\);

-- Location: LCCOMB_X13_Y1_N22
\inst26~5\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst26~5_combout\ = (\inst27~q\ & (\inst29~q\ & (\inst31~q\ & !\inst33~q\))) # (!\inst27~q\ & (\inst33~q\ & (\inst29~q\ $ (\inst31~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000011010000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst31~q\,
	datac => \inst27~q\,
	datad => \inst33~q\,
	combout => \inst26~5_combout\);

-- Location: LCCOMB_X13_Y1_N10
\inst26~6\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst26~6_combout\ = (\inst29~q\ & (!\inst31~q\ & (\inst33~q\ $ (!\inst27~q\)))) # (!\inst29~q\ & (!\inst33~q\ & ((\inst31~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0001000110000010",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst29~q\,
	datab => \inst33~q\,
	datac => \inst27~q\,
	datad => \inst31~q\,
	combout => \inst26~6_combout\);

-- Location: LCCOMB_X13_Y1_N8
\inst26~4\ : cycloneiv_lcell_comb
-- Equation(s):
-- \inst26~4_combout\ = (\inst26~5_combout\) # ((\inst26~6_combout\) # ((\b~input_o\ & \inst~0_combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111111111111000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \b~input_o\,
	datab => \inst~0_combout\,
	datac => \inst26~5_combout\,
	datad => \inst26~6_combout\,
	combout => \inst26~4_combout\);

-- Location: FF_X13_Y1_N9
inst27 : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \inst26~4_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \inst27~q\);

ww_q(3) <= \q[3]~output_o\;

ww_q(2) <= \q[2]~output_o\;

ww_q(1) <= \q[1]~output_o\;

ww_q(0) <= \q[0]~output_o\;
END structure;


