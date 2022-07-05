module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 7) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 1) regB 
    , Nop 
    , Nop 
    , Nop 
    , Load (IndAddr regSP) regA 
    , Load (ImmValue 3) regC 
    , Compute Add regC regSP regC 
    , Load (IndAddr regC) regD 
    , Compute Equal regA regD regA 
    , Compute And regA regB regB 
    , Pop regE 
    , Load (IndAddr regSP) regA 
    , Load (ImmValue 3) regC 
    , Compute Add regC regSP regC 
    , Load (IndAddr regC) regD 
    , Compute Equal regA regD regA 
    , Compute And regA regB regB 
    , Pop regE 
    , Load (IndAddr regSP) regA 
    , Load (ImmValue 3) regC 
    , Compute Add regC regSP regC 
    , Load (IndAddr regC) regD 
    , Compute Equal regA regD regA 
    , Compute And regA regB regB 
    , Pop regE 
    , Pop regE 
    , Pop regE 
    , Pop regE 
    , Push regB 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

