module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 3) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 2) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regA 
    , Pop regD 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

